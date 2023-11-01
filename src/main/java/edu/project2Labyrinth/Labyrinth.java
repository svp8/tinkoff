package edu.project2Labyrinth;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private final List<Cell> cells;
    private final int height;

    public int getWidth() {
        return width;
    }

    private final int width;

    public Labyrinth(int height, int width, List<Cell> cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
    }

    public static List<Cell> neighbourList(Cell cell, List<Cell> grid) {
        Coordinate coordinate = cell.getCoordinate();
        int x = coordinate.x();
        int y = coordinate.y();
        List<Cell> neighbours = new ArrayList<>();
        Cell top = checkNeighbourInGridBounds(grid, new Cell(y - 1, x));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(y, x + 1));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(y + 1, x));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(y, x - 1));
        if (top != null) {
            neighbours.add(top);
        }
        if (right != null) {
            neighbours.add(right);
        }
        if (bottom != null) {
            neighbours.add(bottom);
        }
        if (left != null) {
            neighbours.add(left);
        }
        return neighbours;
    }

    public static Cell checkNeighbourInGridBounds(List<Cell> grid, Cell neighbour) {
        if (grid.contains(neighbour)) {
            return grid.get(grid.indexOf(neighbour));
        } else {
            return null;
        }
    }

    public static List<Cell> getValidNeighbour(Cell cell, List<Cell> grid) {
        List<Cell> neighbours = new ArrayList<>();
        boolean[] walls = grid.get(grid.indexOf(cell)).getWalls();
        Coordinate coordinate = cell.getCoordinate();
        int x = coordinate.x();
        int y = coordinate.y();
        Cell top = checkNeighbourInGridBounds(grid, new Cell(y - 1, x));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(y, x + 1));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(y + 1, x));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(y, x - 1));
        if (top != null) {
            if (!walls[0]) {
                neighbours.add(top);
            }
        }
        if (right != null) {
            if (!walls[1]) {
                neighbours.add(right);
            }
        }
        if (bottom != null) {
            if (!walls[2]) {
                neighbours.add(bottom);
            }
        }
        if (left != null) {
            if (!walls[3]) {
                neighbours.add(left);
            }
        }
        return neighbours;
    }
    public List<Cell> getCells() {
        return cells;
    }

    public int getHeight() {
        return height;
    }
}
