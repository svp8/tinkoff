package edu.hw2.task2;

public class Square extends Rectangle {

    @Override
    public Rectangle setWidth(int width) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        return rectangle;
    }

    @Override
    public Rectangle setHeight(int height) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        return rectangle;
    }
}
