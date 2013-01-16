package me.rabierre.forkjoin;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 11.
 * Time: 오후 3:36
 * To change this template use File | Settings | File Templates.
 */
public class SimpleBlur extends Blur{

    public SimpleBlur() {
        super();
    }

    public SimpleBlur(int[] source, int start, int length, int[] destination) {
        super(source, start, length, destination);

    }

    @Override
    public void blur(int[] src, int[] dst) {
        SimpleBlur blur = new SimpleBlur(src, 0, src.length, dst);

        long startTime = System.currentTimeMillis();
        blur.computeDirectly();
        long endTime = System.currentTimeMillis();

        System.out.println("Simple blur took " + (endTime - startTime) + " milliseconds.");
    }
}
