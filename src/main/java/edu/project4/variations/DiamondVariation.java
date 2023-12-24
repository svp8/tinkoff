package edu.project4.variations;

import edu.project4.Point;

public class DiamondVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double t = Math.atan(x / y);
        double newX = Math.sin(t) * Math.cos(r);
        double newY = Math.sin(r) * Math.cos(t);
        return new Point(newX, newY);
    }
}
