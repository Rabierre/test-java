package me.rabierre.forkjoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 11.
 * Time: 오후 3:46
 * To change this template use File | Settings | File Templates.
 */
public class RecursiveThreadBlur extends Thread implements Runnable {
    protected int[] source;
    protected int[] destination;
    protected int start;
    protected int length;

    protected int blurWidth = 15;

    private static List<Thread> threadList = new ArrayList<Thread>();   // todo static thread list is dangerous.

    public RecursiveThreadBlur(int[] source, int start, int length, int[] destination) {
        this.source = source;
        this.start = start;
        this.length = length;
        this.destination = destination;
    }

    private static int sThreshold = 10000;

    private synchronized void taskDived() {
        if (length < sThreshold) {
            threadList.add(this);
            return;
        }

        final int split = length / 2;

        new RecursiveThreadBlur(source, start, split, destination).taskDived();
        new RecursiveThreadBlur(source, start + split, length - split, destination).taskDived();
    }

    public void compute() throws InterruptedException {
        for (Thread t : threadList) {
            t.start();
        }
        // don't use iterator here. ConcurrentModificationException throw
        for (int i = 0; i < threadList.size(); i++) {
            System.out.println("THREAD LIST SIZE : " + threadList.size());
            System.out.println("count" + i); // todo remove
            threadList.get(i).join();
        }
    }

    public int[] blur() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        taskDived();    // dived task into small pieces enough to execute and assign to thread
        compute();
        long endTime = System.currentTimeMillis();

        System.out.println("Recursive Thread blur took " + (endTime - startTime) + " milliseconds.");

        return destination;
    }

    @Override
    public void run() {
        int sidePixels = (blurWidth - 1) / 2;
        for (int index = start; index < start + length; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0), source.length - 1);
                int pixel = source[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16) / blurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8) / blurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0) / blurWidth;
            }

            // Re-assemble destination pixel.
            int dpixel = (0xff000000) |
                    (((int) rt) << 16) |
                    (((int) gt) << 8) |
                    (((int) bt) << 0);
            destination[index] = dpixel;
        }
    }
}
