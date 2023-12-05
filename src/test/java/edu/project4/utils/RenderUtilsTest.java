package edu.project4.utils;

import edu.project4.Affine;
import edu.project4.Pixel;
import edu.project4.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

class RenderUtilsTest {

    @Test
    @DisplayName("Should rotate 180")
    void rotate() {
        Point point=new Point(1.0,1.0);

        Point actual=RenderUtils.rotate(point,Math.PI);

        Assertions.assertEquals(point.x()*-1,Math.ceil(actual.x()));
        Assertions.assertEquals(point.y()*-1,Math.floor(actual.y()));
    }

    @Test
    void updatePixelColor() {
        Pixel pixel=new Pixel(new Color(255,255,255));
        pixel.setHitCount(0);
        Affine affine=new Affine(new Color(2,2,2));

        RenderUtils.updatePixelColor(pixel,affine);

        Assertions.assertEquals(new Color(2,2,2),pixel.getColor());
    }
    @Test
    @DisplayName("Hit count more than 0")
    void updatePixelColorHitCount() {
        Pixel pixel=new Pixel(new Color(255,254,253));
        pixel.setHitCount(1);
        Affine affine=new Affine(new Color(2,2,1));

        RenderUtils.updatePixelColor(pixel,affine);

        Assertions.assertEquals(new Color((255+2)/2,(254+2)/2,(253+1)/2),pixel.getColor());
    }
}
