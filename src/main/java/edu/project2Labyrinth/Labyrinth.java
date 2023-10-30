package edu.project2Labyrinth;

import java.util.List;

public class Labyrinth {
    private final List<Cell> cells;
    private int height;
    private int width;

    public Labyrinth(int height,int width,List<Cell> cells) {
        this.height=height;
        this.width=width;
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
