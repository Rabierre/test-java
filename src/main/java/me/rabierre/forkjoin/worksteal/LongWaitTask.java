package me.rabierre.forkjoin.worksteal;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 18.
 * Time: 오후 10:23
 * To change this template use File | Settings | File Templates.
 */
public class LongWaitTask implements Task {
    static int WAIT_TIME = 200;

    @Override
    public void run() throws InterruptedException {
        //System.out.println("LongWaitTask!");
        sleep(WAIT_TIME);
    }
}
