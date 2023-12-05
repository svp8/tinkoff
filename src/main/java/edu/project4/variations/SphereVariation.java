package edu.project4.variations;

import edu.project4.Point;

public class SphereVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double radius = 1.0 / (x * x + y * y);
        double newX = radius * x;
        double newY = radius * y;
        return new Point(newX, newY);
    }
}
