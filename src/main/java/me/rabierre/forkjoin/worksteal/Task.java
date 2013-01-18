package me.rabierre.forkjoin.worksteal;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 18.
 * Time: 오후 10:33
 * To change this template use File | Settings | File Templates.
 */
public interface Task {
    void run() throws InterruptedException;
}
