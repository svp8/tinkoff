package edu.project4.variations;

import edu.project4.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        double newX = x * sin(r * r) - y * cos(r * r);
        double newY = x * cos(r * r) + y * sin(r * r);
        return new Point(newX, newY);
    }
}
