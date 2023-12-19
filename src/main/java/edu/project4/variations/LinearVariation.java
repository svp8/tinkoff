package edu.project4.variations;

import edu.project4.Point;

public class LinearVariation implements Variation {
    private double amt;

    public LinearVariation(double amt) {
        this.amt = amt;
    }

    @Override
    public Point compute(double x, double y) {
        return new Point(x / amt, y / amt);
    }
}
