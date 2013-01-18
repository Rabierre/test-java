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
public class ThreadPoolBlur {
    protected int[] source;
    protected int[] destination;
    protected int start;
    protected int length;

    protected int blurWidth = 15;

    private final int THREAD_NUMBER = 10;

    public ThreadPoolBlur(int[] source, int start, int length, int[] destination) {
        this.source = source;
        this.destination = destination;
        this.start = start;
        this.length = length;
    }

    public void computeDirectly() {
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

    public void blur() {
        Thread[] threadPool = new Thread[THREAD_NUMBER];
        for (int i = 0; i < threadPool.length; i++) {
            int split = (i != THREAD_NUMBER - 1) ? (source.length / THREAD_NUMBER) : (source.length % THREAD_NUMBER);

            BlurRunner runner = new BlurRunner(new ThreadPoolBlur(source, split * i, split, destination));
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

    public static void main(String[] args) throws Exception {
        String filename = "ngc_trees and the blue pond.jpg";
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        new ImageFrame("ThreadPool - original", image);

        BufferedImage blurredImage = blur(image);

        new ImageFrame("ThreadPool - processed", blurredImage);
    }

    public static BufferedImage blur(BufferedImage srcImage) throws InterruptedException {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor" +
                (processors != 1 ? "s are " : " is ") +
                "available");

        /** START */
        ThreadPoolBlur threadPoolBlur = new ThreadPoolBlur(src, 0, src.length, dst);

        long startTime = System.currentTimeMillis();
        threadPoolBlur.blur();
        long endTime = System.currentTimeMillis();
        /** END */

        System.out.println("Image blur took " + (endTime - startTime) + " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
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
