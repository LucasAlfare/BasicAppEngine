package com.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private final JFrame frame;
    private final BufferedImage image;
    private final Canvas canvas;
    private final BufferStrategy strategy;
    private final Graphics g; //aux to draw the main image of the window

    public Window(Engine engine) {
        image = toCompatibleImage(new BufferedImage(engine.getWidth(), engine.getHeight(), BufferedImage.TYPE_INT_RGB));

        canvas = new Canvas();
        int canvasWidth = (int) (engine.getWidth() * engine.getScale());
        int canvasHeight = (int) (engine.getHeight() * engine.getScale());
        Dimension d = new Dimension(canvasWidth, canvasHeight);

        frame = new JFrame("Application"); //should receive a good title from param...
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);

        frame.setResizable(false);
        //frame.setUndecorated(true);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);

        canvas.setSize(d);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        frame.pack();
        frame.setLocationRelativeTo(null);

        strategy = canvas.getBufferStrategy();
        g = strategy.getDrawGraphics();
    }

    public void render() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        strategy.show();
    }

    //source: https://stackoverflow.com/a/197060/4563960
    private BufferedImage toCompatibleImage(BufferedImage image) {
        // obtain the current system graphical settings
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        /*
         * if image is already compatible and optimized for current system
         * settings, simply return it
         */
        if (image.getColorModel().equals(gfxConfig.getColorModel()))
            return image;

        // image is not optimized, so create a new image that is
        BufferedImage newImage = gfxConfig.createCompatibleImage(
                image.getWidth(), image.getHeight(), image.getTransparency());

        // get the graphics context of the new image to draw the old image on
        Graphics2D g2d = newImage.createGraphics();

        // actually draw the image and dispose of context no longer needed
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // return the new optimized image
        return newImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
