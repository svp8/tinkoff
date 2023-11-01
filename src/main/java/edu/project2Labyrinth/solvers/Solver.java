package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;

import java.util.List;

public interface Solver {
    List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2);
}
