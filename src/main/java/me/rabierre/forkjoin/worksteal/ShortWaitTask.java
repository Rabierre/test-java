package me.rabierre.forkjoin.worksteal;

import static java.lang.Thread.sleep;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 18.
 * Time: 오후 10:23
 * To change this template use File | Settings | File Templates.
 */
public class ShortWaitTask implements Task {
    static int WAIT_TIME = 100;

    @Override
    public void run() throws InterruptedException {
        //System.out.println("ShortWaitTask!");
        sleep(WAIT_TIME);
    }
}
