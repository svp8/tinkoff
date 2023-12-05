package edu.project4.renderer;

import edu.project4.Affine;
import edu.project4.FlameImage;
import edu.project4.variations.Variation;
import java.util.List;

public interface Renderer {
    FlameImage render(
        FlameImage canvas,
        List<Variation> variations,
        List<Affine> affines,
        int samples,
        int iterPerSample,
        int symmetry
    );

}
