package edu.project2Labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Labyrinth(int height, int width, List<Cell> cells) {

    public static final int LEFT = 3;

    public Labyrinth(int height, int width, List<Cell> cells) {
        this.height = height;
        this.width = width;
        this.cells = Collections
            .unmodifiableList(cells);
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
        int index = grid.indexOf(neighbour);
        if (index != -1) {
            return grid.get(index);
        } else {
            return null;
        }
    }

    public static List<Cell> getValidNeighbours(Cell cell, List<Cell> grid) {
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
            if (!walls[LEFT]) {
                neighbours.add(left);
            }
        }
        return neighbours;
    }

    public static void removeWalls(Cell current, Cell next) {
        Coordinate coordinateCurrent = current.getCoordinate();
        Coordinate coordinateNext = next.getCoordinate();
        boolean[] wallsCurrent = current.getWalls();
        boolean[] wallsNext = next.getWalls();
        int x = coordinateCurrent.x() - coordinateNext.x();
        // top 0, right 1, bottom 2, left 3

        if (x == 1) {
            wallsCurrent[LEFT] = false;
            wallsNext[1] = false;
        } else if (x == -1) {
            wallsCurrent[1] = false;
            wallsNext[LEFT] = false;
        }

        int y = coordinateCurrent.y() - coordinateNext.y();

        if (y == 1) {
            wallsCurrent[0] = false;
            wallsNext[2] = false;
        } else if (y == -1) {
            wallsCurrent[2] = false;
            wallsNext[0] = false;
        }
    }
}
