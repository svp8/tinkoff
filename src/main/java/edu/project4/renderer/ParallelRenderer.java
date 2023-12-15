package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.variations.SwirlVariation;
import edu.project4.variations.Variation;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelRenderer implements Renderer {
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
        variations.add(new SwirlVariation());
        List<Affine> affines = Affine.generateAffine(16, random);
        FlameImage createdImage =
            fractalFlameGenerator.render(flameImage, variations, affines, 500000,
                20, 2
            );
        createdImage.createImageFile("image");
        createdImage.correct(1.7);
        createdImage.createImageFile("correctedImage");
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
            SimpleRenderer simpleRenderer = new SimpleRenderer();
            for (int i = 0; i < threadCount; i++) {
                int finalMod = mod;
                executorService.execute(() -> simpleRenderer.render(
                    canvas,
                    variations,
                    affines,
                    samples / threadCount + finalMod,
                    iterPerSample,
                    symmetry
                ));
                mod = 0;
            }
        }
        return canvas;
    }
}
