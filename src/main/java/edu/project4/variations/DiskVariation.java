package edu.project4.variations;

import edu.project4.Point;

public class DiskVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double a = 1 / Math.PI * Math.atan(y / x);
        return new Point(
            a * Math.sin(Math.PI * r),
            a * Math.cos(Math.PI * r)
        );
    }
}
