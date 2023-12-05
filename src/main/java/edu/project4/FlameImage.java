package edu.project4;

import java.awt.Color;

public class FlameImage {
    private int height;
    private int width;
    private final Pixel[][] pixels;

    public FlameImage(int height, int width) {
        this.height = height;
        this.width = width;
        pixels = new Pixel[height][width];
        int y = -1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new Pixel(new Color(0));
            }
        }
    }

    public boolean containsPixel(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public Pixel getPixel(int x, int y) {
        if (!containsPixel(x, y)) {
            return null;
        }
        return pixels[y][x];
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

}
