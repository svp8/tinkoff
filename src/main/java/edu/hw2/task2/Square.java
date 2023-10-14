package edu.hw2.task2;

public class Square extends Rectangle {
    private boolean changed = false;

    @Override
    public Rectangle setWidth(int width) {
        if (!changed) {
            super.setWidth(width);
            super.setHeight(width);
            changed = true;
            return this;
        } else {
            Rectangle temp = new Rectangle();
            temp.setWidth(width);
            temp.setHeight(super.getHeight());
            return temp;
        }

    }

    @Override
    public Rectangle setHeight(int height) {
        if (!changed) {
            super.setWidth(height);
            super.setHeight(height);
            changed = true;
            return this;
        } else {
            Rectangle temp = new Rectangle();
            temp.setWidth(getWidth());
            temp.setHeight(height);
            return temp;
        }
    }
}
