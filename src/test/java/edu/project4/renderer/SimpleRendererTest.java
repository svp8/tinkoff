package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.utils.Correction;
import edu.project4.utils.ImageUtils;
import edu.project4.variations.LinearVariation;
import edu.project4.variations.SphereVariation;
import edu.project4.variations.Variation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SimpleRendererTest {
    private static  final Logger LOGGER= LogManager.getLogger();
    @Test
    void render() throws IOException {
        Renderer renderer=new SimpleRenderer();
        FlameImage image=new FlameImage(1080,1920);
        List<Affine> affines=Affine.generateAffine(16,new Random());
        List<Variation> variations=new ArrayList<>();
        variations.add(new LinearVariation(20));
        variations.add(new SphereVariation());
        long start=System.nanoTime();
//        FlameImage createdImage =
//            renderer.render(image, variations, affines, 1000000,
//                20, 3
//            );
        FlameImage createdImage =
            renderer.render(image, variations, affines, 1000,
                20, 3
            );
        long end=System.nanoTime();
        ImageUtils.createImageFile(createdImage, "image_simple");
        Correction.correct(createdImage, 1.2);
        ImageUtils.createImageFile(createdImage, "correctedImage_simple");
        LOGGER.info("Time of execution 1 thread: "+((end-start)/1_000_000));
    }
    //time - 73021
}
