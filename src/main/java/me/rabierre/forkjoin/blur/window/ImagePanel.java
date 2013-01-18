package me.rabierre.forkjoin.blur.window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 11.
 * Time: 오후 4:44
 * To change this template use File | Settings | File Templates.
 */
public class ImagePanel extends JPanel {
    BufferedImage mImage;

    public ImagePanel(BufferedImage image) {
        mImage = image;
    }

    protected void paintComponent(Graphics g) {
        int x = (getWidth() - mImage.getWidth())/2;
        int y = (getHeight() - mImage.getHeight())/2;
        g.drawImage(mImage, x, y, this);
    }
}
