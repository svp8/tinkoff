package edu.project4.variations;

import edu.project4.Point;

public class SpiralVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double o = Math.atan(x / y);
        return new Point((Math.cos(o) + Math.sin(r)) / r, (Math.sin(o) - Math.cos(r)) / r);
    }
}
