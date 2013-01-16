package me.rabierre.forkjoin;

import me.rabierre.forkjoin.window.ImageFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 12.
 * Time: 오후 1:40
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor" +
                (processors != 1 ? "s are " : " is ") +
                "available");

        String filename = "ngc_trees and the blue pond.jpg";
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);

        int w = image.getWidth();
        int h = image.getHeight();
        int[] src = image.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);

        // process blur
        new ImageFrame("Blur - original", image);

        BufferedImage blurredImage = null;

/*
        blurredImage = blur(src, dst, w, h, SimpleBlur.class);
        new ImageFrame("SimpleBlur - processed", blurredImage);

        blurredImage = blur(src, dst, w, h, ThreadPoolBlur.class);
        new ImageFrame("ThreadPoolBlur - processed", blurredImage);
*/

        try {
            new RecursiveThreadBlur(src, 0, src.length, dst).blur();

            blurredImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            blurredImage.setRGB(0, 0, w, h, dst, 0, w);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ImageFrame("RecursiveThreadBlur - processed", blurredImage);
    }

    public static BufferedImage blur(int[] src, int[] dst, int width, int height, Class blur) {
        try {
            Method method = blur.getMethod("blur", int[].class, int[].class);
            method.invoke(ClassLoader.getSystemClassLoader().loadClass(blur.getName()).newInstance(), src, dst);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }

        BufferedImage dstImage =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, width, height, dst, 0, width);

        return dstImage;
    }
}
