package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.utils.RenderUtils;
import edu.project4.variations.Variation;
import java.security.SecureRandom;
import java.util.List;

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
        SecureRandom random = new SecureRandom();
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        for (int sample = 0; sample < samples; sample++) {
            Point point = RenderUtils.randomPoint(XMAX, YMAX, XMIN, YMIN, random);
            for (int step = 0; step < iterPerSample; step++) {
                Affine affine = affines.get(random.nextInt(affines.size()));
                Variation variation = RenderUtils.getRandomVariation(variations, random);
                point = affine.apply(point);
                point = variation.compute(point.x(), point.y());
                double t = 0.0;
                for (int s = 0; s < symmetry; t += Math.PI * 2 / symmetry, s++) {
                    Point rotated = RenderUtils.rotate(point, t);
                    int x1 = width - (int) (((XMAX - rotated.x()) / (XMAX - XMIN)) * width);
                    int y1 = height - (int) (((YMAX - rotated.y()) / (YMAX - YMIN)) * height);
                    Pixel pixel = canvas.getPixel(x1, y1);
                    if (pixel == null) {
                        continue;
                    }
                    RenderUtils.updatePixelColor(pixel, affine);
                }
            }
        }

        return canvas;
    }
}
