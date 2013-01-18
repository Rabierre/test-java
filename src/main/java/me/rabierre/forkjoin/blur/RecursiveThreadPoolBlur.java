package me.rabierre.forkjoin.blur;

import me.rabierre.forkjoin.blur.window.ImageFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 11.
 * Time: 오후 3:46
 * To change this template use File | Settings | File Templates.
 */
public class RecursiveThreadPoolBlur implements Runnable {
    protected int[] source;
    protected int[] destination;
    protected int start;
    protected int length;

    protected int blurWidth = 15;

    private static List<Thread> threadList = new ArrayList<Thread>();   // todo static thread list is dangerous.

    public RecursiveThreadPoolBlur(int[] source, int start, int length, int[] destination) {
        this.source = source;
        this.start = start;
        this.length = length;
        this.destination = destination;
    }

    private static int sThreshold = 10000;

    private synchronized void divedTask() {
        if (length < sThreshold) {
            threadList.add(new Thread(this));
            return;
        }

        final int split = length / 2;

        new RecursiveThreadPoolBlur(source, start, split, destination).divedTask();
        new RecursiveThreadPoolBlur(source, start + split, length - split, destination).divedTask();
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
        divedTask();    // dived task into small pieces enough to execute and assign to thread
        compute();      // parallel compute
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

    public static void main(String[] args) throws Exception {
        String filename = "ngc_trees and the blue pond.jpg";
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        new ImageFrame("RecursiveThreadPoolBlur - original", image);

        BufferedImage blurredImage = blur(image);

        new ImageFrame("RecursiveThreadPoolBlur - processed", blurredImage);
    }

    public static BufferedImage blur(BufferedImage srcImage) throws InterruptedException {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + sThreshold);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor" +
                (processors != 1 ? "s are " : " is ") +
                "available");

        /** START */
        RecursiveThreadPoolBlur recursiveThreadBlur = new RecursiveThreadPoolBlur(src, 0, src.length, dst);

        long startTime = System.currentTimeMillis();
        recursiveThreadBlur.blur();
        long endTime = System.currentTimeMillis();
        /** END */

        System.out.println("Image blur took " + (endTime - startTime) + " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
}
