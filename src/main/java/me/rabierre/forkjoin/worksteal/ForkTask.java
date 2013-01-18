package me.rabierre.forkjoin.worksteal;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 18.
 * Time: 오후 10:27
 * To change this template use File | Settings | File Templates.
 */
public class ForkTask extends RecursiveAction {
    private Task[] tasks;
    private int start;
    private int length;

    ForkTask(Task[] tasks, int start, int length) {
        this.tasks = tasks;
        this.start = start;
        this.length = length;
    }

    private void computeDirectly() throws InterruptedException {
        for (int i = start; i < start + length; i++) {
            tasks[i].run();
        }
    }

    private static int threshold = 1000;

    @Override
    protected void compute() {
        if (length < threshold) {
            try {
                computeDirectly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("Running Thread Count : " + getPool().getRunningThreadCount());

        int split = length / 2;
        invokeAll(new ForkTask(tasks, start, split),
                new ForkTask(tasks, split + start, length - split));
    }

    public static void main(String[] args) {
        int TASK_COUNT = 1000;
        Task[] longWaitTasks = new Task[TASK_COUNT];
        Task[] shortWaitTasks = new Task[TASK_COUNT];

        for (int i = 0; i < TASK_COUNT; i++) {
            longWaitTasks[i] = new LongWaitTask();
            shortWaitTasks[i] = new ShortWaitTask();
        }

        Task[] tasks = new Task[TASK_COUNT * 2];
        System.arraycopy(longWaitTasks, 0, tasks, 0, TASK_COUNT);
        System.arraycopy(shortWaitTasks, 0, tasks, TASK_COUNT, TASK_COUNT);

        /** start */
        ForkTask forkTask = new ForkTask(tasks, 0, tasks.length);

        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        pool.invoke(forkTask);
        long endTime = System.currentTimeMillis();
        /** info idle time took was 75secs, actual was 100.488secs */
        System.out.println("total time : " + (endTime - startTime)/1000 + "seconds");
        /** info steal count is only 4. what this means? */
        System.out.println("Work steal count : " + pool.getStealCount());
    }
}
