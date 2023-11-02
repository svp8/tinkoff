package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static edu.project2Labyrinth.Labyrinth.getValidNeighbours;

public class BFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2) {
        Deque<Coordinate> queue = new ArrayDeque<>();
        List<Cell> cells = labyrinth.cells();
        boolean[][] visited = new boolean[labyrinth.height()][labyrinth.width()];
        queue.offer(coordinate1);
        Map<Coordinate, Coordinate> relations = new HashMap<>();
        //Начало пути
        relations.put(coordinate1, null);
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            visited[current.y()][current.x()] = true;
            if (current.equals(coordinate2)) {
                break;
            }
            List<Cell> neighbours = getValidNeighbours(new Cell(current.y(), current.x()), cells);
            for (Cell neighbour : neighbours) {
                Coordinate coordinate = neighbour.getCoordinate();
                if (!visited[coordinate.y()][coordinate.x()]) {
                    relations.put(neighbour.getCoordinate(), current);
                    queue.offer(neighbour.getCoordinate());
                }
            }
        }
        return createPath(relations, coordinate2);
    }
}
