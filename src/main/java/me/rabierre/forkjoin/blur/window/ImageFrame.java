package me.rabierre.forkjoin.blur.window;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 1. 11.
 * Time: 오후 4:44
 * To change this template use File | Settings | File Templates.
 */
public class ImageFrame extends JFrame {
    public ImageFrame(String title, BufferedImage image) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(image.getWidth(), image.getHeight());
        add(new ImagePanel(image));
        setLocationByPlatform(true);
        setVisible(true);
    }
}
