package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.variations.LinearVariation;
import edu.project4.variations.SphereVariation;
import edu.project4.variations.Variation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParallelRendererTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    void render(int threadCount) throws IOException {
        Renderer renderer = new ParallelRenderer(threadCount);
        FlameImage image = new FlameImage(1080, 1920);
        List<Affine> affines = Affine.generateAffine(16, new Random());
        List<Variation> variations = new ArrayList<>();
        variations.add(new LinearVariation(20));
        variations.add(new SphereVariation());
        long start = System.nanoTime();
        FlameImage createdImage =
            renderer.render(image, variations, affines, 1000,
                20, 3
            );
        long end = System.nanoTime();
        createdImage.createImageFile("image");
        createdImage.correct(1.2);
        createdImage.createImageFile("correctedImage");
        LOGGER.info("Time of execution " + threadCount + " threads: " + ((end - start) / 1_000_000));
    }
    //2 threads  7484
    //3 threads  4980
    //4 threads  4486
}
