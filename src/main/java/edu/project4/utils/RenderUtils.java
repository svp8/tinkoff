package edu.project4.utils;

import edu.project4.Affine;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.variations.Variation;
import java.awt.Color;
import java.util.List;
import java.util.Random;

public final class RenderUtils {
    private RenderUtils() {
    }

    public static Point randomPoint(double xMax, double yMax, double xMin, double yMin, Random random) {
        return new Point(random.nextDouble(xMin, xMax), random.nextDouble(yMin, yMax));
    }

    public static Variation getRandomVariation(List<Variation> variations, Random random) {
        return variations.get(random.nextInt(variations.size()));
    }

    public static Point rotate(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);
        return new Point(x, y);
    }

    public static void updatePixelColor(Pixel pixel, Affine affine) {
        Color affineColor = affine.getColor();
        Color color;
        if (pixel.getHitCount() == 0) {
            color = affineColor;
        } else {
            Color currentColor = pixel.getColor();
            color = new Color(
                (currentColor.getRed() + affineColor.getRed()) / 2,
                (currentColor.getGreen() + affineColor.getGreen()) / 2,
                (currentColor.getBlue() + affineColor.getBlue()) / 2
            );
        }
        pixel.setColor(color);
        pixel.setHitCount(pixel.getHitCount() + 1);
    }
}
