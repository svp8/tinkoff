package edu.project2Labyrinth;

import java.util.Objects;

public class Cell {
    private final boolean[] walls={true,true,true,true};
    private Coordinate coordinate;
    private boolean visited=false;

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(coordinate, cell.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    public Cell(int y,int x) {
        this.coordinate = new Coordinate(y,x);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean[] getWalls() {
        return walls;
    }
}
