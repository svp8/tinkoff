package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class FlameImage {
    private int height;
    private int width;
    private final Pixel[][] pixels;
    public static final int ALPHA = 200;
    private static final int A = 24;
    private static final int R = 16;
    private static final int G = 8;
    private static final Logger LOGGER = LogManager.getLogger();

    public FlameImage(int height, int width) {
        this.height = height;
        this.width = width;
        pixels = new Pixel[height][width];
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

    public void correct(double gamma) { //чем меньше значение, тем темнее
        double max = Double.MIN_VALUE;
        int xRes = getWidth();
        int yRes = getHeight();
        for (int row = 0; row < yRes; row++) {
            for (int col = 0; col < xRes; col++) {
                if (pixels[row][col].getHitCount() != 0) {
                    double normal = log10(pixels[row][col].getHitCount());
                    if (normal > max) {
                        max = normal;
                    }
                }
            }
        }
        for (int row = 0; row < yRes; row++) {
            for (int col = 0; col < xRes; col++) {
                double normal = log10(pixels[row][col].getHitCount()) / max;
                Color current = pixels[row][col].getColor();
                Color updated = new Color(
                    (int) (current.getRed() * pow(normal, 1.0 / gamma)),
                    (int) (current.getGreen() * pow(normal, 1.0 / gamma)),
                    (int) (current.getBlue() * pow(normal, 1.0 / gamma))
                );
                pixels[row][col].setColor(updated);
            }
        }
    }

    public File createImageFile(String filename) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel currentPixel = pixels[i][j];
                int color =
                    (ALPHA << A) | (currentPixel.getColor().getRed() << R) | (currentPixel.getColor().getRed() << G)
                        | currentPixel.getColor().getBlue();
                bufferedImage.setRGB(j, i, color);
            }
        }
        // Save as PNG
        File file = new File("src/main/resources/" + filename + ".png");
        ImageIO.write(bufferedImage, "png", file);
        LOGGER.info("File created: " + file.getAbsolutePath());
        return file;
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


}
