package edu.project4.variations;

import edu.project4.Point;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class HeartVariation implements Variation {
    @Override
    public Point compute(double x, double y) {
        double r = x * x + y * y;
        double theta = atan(x / y);
        return new Point(r * sin(theta * r), -r * cos(theta * r));
    }
}
