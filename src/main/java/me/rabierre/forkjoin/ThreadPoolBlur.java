package me.rabierre.forkjoin;

import me.rabierre.forkjoin.window.ImageFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 12.
 * Time: 오후 1:39
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPoolBlur extends Blur {
    private final int THREAD_NUMBER = 10;

    public ThreadPoolBlur() {
        super();
    }

    public ThreadPoolBlur(int[] source, int start, int length, int[] destination) {
        super(source, start, length, destination);
    }

    public void blur(int[] src, int[] dst) {
        Thread[] threadPool = new Thread[THREAD_NUMBER];
        for (int i = 0; i < threadPool.length; i++) {
            int split = (i != THREAD_NUMBER - 1) ? (src.length / THREAD_NUMBER) : (src.length % THREAD_NUMBER);

            BlurRunner runner = new BlurRunner(new ThreadPoolBlur(src, split * i, split, dst));
            threadPool[i] = new Thread(runner);
        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : threadPool) {
            thread.start();
        }
        try {
            for (Thread thread : threadPool) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Thread Pool blur took " + (endTime - startTime) + " milliseconds. Thread Count is " + THREAD_NUMBER);
    }
}

class BlurRunner implements Runnable {
    ThreadPoolBlur blur;

    BlurRunner(ThreadPoolBlur blur) {
        this.blur = blur;
    }

    @Override
    public void run() {
        blur.computeDirectly();
    }
}
