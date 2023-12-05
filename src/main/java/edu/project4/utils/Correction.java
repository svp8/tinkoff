package edu.project4.utils;

import edu.project4.FlameImage;
import edu.project4.Pixel;
import java.awt.Color;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public final class Correction {
    private Correction() {
    }

    public static void correct(FlameImage image, double gamma) { //чем меньше значение, тем темнее
        double max = 0.0;
        int xRes = image.getWidth();
        int yRes = image.getHeight();
        Pixel[][] pixels = image.getPixels();
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
}
