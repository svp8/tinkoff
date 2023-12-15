package edu.project4;

import java.awt.Color;

public class Pixel {
    private int hitCount;
    private Color color;

    public Pixel(Color color) {
        this.color = color;
    }

    public synchronized void updatePixelColor(Affine affine) {
        Color affineColor = affine.getColor();
        Color resultColor;
        if (getHitCount() == 0) {
            resultColor = affineColor;
        } else {
            Color currentColor = this.color;
            resultColor = new Color(
                (currentColor.getRed() + affineColor.getRed()) / 2,
                (currentColor.getGreen() + affineColor.getGreen()) / 2,
                (currentColor.getBlue() + affineColor.getBlue()) / 2
            );
        }
        setColor(resultColor);
        setHitCount(this.hitCount + 1);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

}
