package me.rabierre.forkjoin;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 12.
 * Time: 오후 11:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class Blur {
    protected int[] source;
    protected int[] destination;
    protected int start;
    protected int length;

    protected int blurWidth = 15;

    public Blur() {
    }

    public Blur(int[] source, int start, int length, int[] destination) {
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

    public abstract void blur(int[] src, int[] dst);
}
