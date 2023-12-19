package edu.project4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Affine {
    private static final Integer MAX_COLOR_VALUE = 256;
    private static final Integer MIN_VALUE = -1;
    private static final Integer MAX_VALUE = 1;
    public static final double MULTIPLIER = 0.7;

    private final Color color;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public Affine(Color color) {
        this.color = color;
    }

    public Affine(Random random) {
        do {
            do {
                this.a = random.nextDouble(MIN_VALUE, MAX_VALUE);
                this.d = random.nextDouble(MIN_VALUE, MAX_VALUE);
            } while ((a * a + d * d) > 1);
            do {
                this.b = random.nextDouble(MIN_VALUE, MAX_VALUE);
                this.e = random.nextDouble(MIN_VALUE, MAX_VALUE);
            } while ((b * b + e * e) > 1);
        } while ((a * a + b * b + d * d + e * e) > (1 + (a * e - d * b) * (a * e - d * b)));
        c = random.nextDouble(-1, 1);
        f = random.nextDouble(-1, 1);
        color = new Color(
            random.nextInt(0, MAX_COLOR_VALUE),
            random.nextInt(0, MAX_COLOR_VALUE),
            random.nextInt(0, MAX_COLOR_VALUE)
        );
    }

    public static List<Affine> generateAffine(int amount, Random random) {
        List<Affine> affines = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Affine affine = new Affine(random);
            affines.add(affine);
        }
        return affines;
    }

    public Point apply(Point point) {
        double newX = a * point.x() + b * point.y() + c;
        double newY = d * point.x() + e * point.y() + f;
        return new Point(newX * MULTIPLIER, newY * MULTIPLIER);
    }

    public Color getColor() {
        return color;
    }
}
