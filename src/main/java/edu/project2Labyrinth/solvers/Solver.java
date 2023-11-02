package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Solver {
    List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2);

    default List<Coordinate> createPath(Map<Coordinate, Coordinate> relations, Coordinate start) {
        Coordinate current = start;
        List<Coordinate> path = new ArrayList<>();
        path.add(current);
        current = relations.get(current);
        while (current != null) {
            path.addFirst(current);
            current = relations.get(current);
        }
        return path;
    }
}
