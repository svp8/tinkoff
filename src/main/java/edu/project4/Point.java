package edu.project4;

import java.util.Random;

public record Point(double x, double y) {
    public static Point rotate(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);
        return new Point(x, y);
    }

    public static Point randomPoint(double xMax, double yMax, double xMin, double yMin, Random random) {
        return new Point(random.nextDouble(xMin, xMax), random.nextDouble(yMin, yMax));
    }
}
