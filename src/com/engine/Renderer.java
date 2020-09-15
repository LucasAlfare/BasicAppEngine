package com.engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Renderer {

    public Graphics g;
    public Graphics2D g2d;
    private final int imageWidth;
    private final int imageHeight;

    public int[] pixelData;

    public Renderer(Engine engine) {
        BufferedImage img = engine.getWindow().getImage();

        pixelData = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        g = img.getGraphics();
        g2d = img.createGraphics();
        imageWidth = engine.getWindow().getImage().getWidth();
        imageHeight = engine.getWindow().getImage().getHeight();
    }

    public void clear() {
        Arrays.fill(pixelData, 0);
    }

    public boolean setPixel(int x, int y, int value) {
        if ((x < 0 || x >= imageWidth || y < 0 || y >= imageHeight) || value == 0xffff00ff) {
            return false;
        }

        pixelData[x + y * imageWidth] = value;
        return true;
    }

    public int getPixel(int x, int y) {
        if ((x < 0 || x >= imageWidth) || (y < 0 || y >= imageHeight)) {
            return -1;
        }

        return pixelData[x + y * imageWidth];
    }
}
