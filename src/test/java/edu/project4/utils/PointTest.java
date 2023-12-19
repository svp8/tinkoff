package edu.project4.utils;

import edu.project4.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    @DisplayName("Should rotate 180")
    void rotate() {
        Point point = new Point(1.0, 1.0);

        Point actual = Point.rotate(point, Math.PI);

        Assertions.assertEquals(point.x() * -1, Math.ceil(actual.x()));
        Assertions.assertEquals(point.y() * -1, Math.floor(actual.y()));
    }
}
