package edu.project4.variations;

import edu.project4.Point;
import static java.lang.Math.sin;

public class CylinderVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        return new Point(sin(x), y);
    }
}
