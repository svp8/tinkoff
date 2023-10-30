package edu.project2Labyrinth;

import java.util.List;

public interface Solver {
    List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2);
}
