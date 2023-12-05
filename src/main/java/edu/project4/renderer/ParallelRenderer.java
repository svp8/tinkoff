package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.utils.Correction;
import edu.project4.utils.ImageUtils;
import edu.project4.utils.RenderUtils;
import edu.project4.variations.DiamondVariation;
import edu.project4.variations.Variation;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ParallelRenderer implements Renderer {
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    public static final double XMIN = -1.777;
    public static final double XMAX = 1.777;
    public static final double YMIN = -1;
    public static final double YMAX = 1;
    private final int threadCount;

    public ParallelRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    @SuppressWarnings({"magicNumber", "uncommentedMain"})
    public static void main(String[] args) throws IOException {
        FlameImage flameImage = new FlameImage(1080, 1920);
        ParallelRenderer fractalFlameGenerator = new ParallelRenderer(4);
        List<Variation> variations = new ArrayList<>();
        Random random = new SecureRandom();
        variations.add(new DiamondVariation());
        List<Affine> affines = Affine.generateAffine(16, random);
        FlameImage createdImage =
            fractalFlameGenerator.render(flameImage, variations, affines, 500000,
                20, 1
            );
        ImageUtils.createImageFile(createdImage, "image");
        Correction.correct(createdImage, 1.7);
        ImageUtils.createImageFile(createdImage, "correctedImage");
    }

    public void renderSample(
        FlameImage canvas,
        int samplesForThread,
        int iterPerSample,
        List<Affine> affines,
        List<Variation> variations,
        int symmetry
    ) {
        Random random = ThreadLocalRandom.current();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        for (int sample = 0; sample < samplesForThread; sample++) {
            Point point = RenderUtils.randomPoint(XMAX, YMAX, XMIN, YMIN, random);
            for (int step = 0; step < iterPerSample; step++) {
                Affine affine = affines.get(random.nextInt(affines.size()));
                Variation variation = getRandomVariation(variations, random);
                point = affine.apply(point);
                point = variation.compute(point.x(), point.y());
                double t = 0.0;
                for (int s = 0; s < symmetry; t += Math.PI * 2 / symmetry, s++) {
                    Point rotated = rotate(point, t);
                    int x1 = width - (int) (((XMAX - rotated.x()) / (XMAX - XMIN)) * width);
                    int y1 = height - (int) (((YMAX - rotated.y()) / (YMAX - YMIN)) * height);
                    Pixel pixel = canvas.getPixel(x1, y1);
                    if (pixel == null) {
                        continue;
                    }
                    synchronized (pixel) {
                        RenderUtils.updatePixelColor(pixel, affine);
                    }
                }
            }
        }

    }

    public FlameImage render(
        FlameImage canvas,
        List<Variation> variations,
        List<Affine> affines,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            int mod = samples % threadCount;
            for (int i = 0; i < threadCount; i++) {
                int finalMod = mod;
                executorService.execute(() -> renderSample(
                    canvas,
                    samples / threadCount + finalMod,
                    iterPerSample,
                    affines,
                    variations,
                    symmetry
                ));
                mod = 0;
            }
        }
        return canvas;
    }

    private Variation getRandomVariation(List<Variation> variations, Random random) {
        return variations.get(random.nextInt(variations.size()));
    }

    private Point rotate(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);
        return new Point(x, y);
    }
}
