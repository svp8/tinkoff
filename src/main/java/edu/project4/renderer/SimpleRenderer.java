package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.utils.RenderUtils;
import edu.project4.variations.Variation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleRenderer implements Renderer {
    public static final double XMIN = -1.777;
    public static final double XMAX = 1.777;
    public static final double YMIN = -1;
    public static final double YMAX = 1;

    public FlameImage render(
        FlameImage canvas,
        List<Variation> variations,
        List<Affine> affines,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        Random random = ThreadLocalRandom.current();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        for (int sample = 0; sample < samples; sample++) {
            Point point = Point.randomPoint(XMAX, YMAX, XMIN, YMIN, random);
            for (int step = 0; step < iterPerSample; step++) {
                Affine affine = RenderUtils.getRandomElement(affines, random);
                Variation variation = RenderUtils.getRandomElement(variations, random);
                point = affine.apply(point);
                point = variation.compute(point.x(), point.y());
                double t = 0.0;
                for (int s = 0; s < symmetry; t += Math.PI * 2 / symmetry, s++) {
                    Point rotated = Point.rotate(point, t);
                    int x1 = width - (int) (((XMAX - rotated.x()) / (XMAX - XMIN)) * width);
                    int y1 = height - (int) (((YMAX - rotated.y()) / (YMAX - YMIN)) * height);
                    Pixel pixel = canvas.getPixel(x1, y1);
                    if (pixel == null) {
                        continue;
                    }
                    pixel.updatePixelColor(affine);
                }
            }
        }
        return canvas;
    }
}
