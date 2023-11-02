package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static edu.project2Labyrinth.Labyrinth.getValidNeighbours;

public class DFSSolver implements Solver {

    @Override
    public List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2) {
        List<Cell> cells = labyrinth.cells();
        boolean[][] visited = new boolean[labyrinth.height()][labyrinth.width()];
        return dfs(coordinate1, visited, new ArrayList<>(), coordinate2, cells);
    }

    public List<Coordinate> dfs(
        Coordinate start,
        boolean[][] visited,
        List<Coordinate> path,
        Coordinate end,
        List<Cell> cells
    ) {
        Coordinate current = start;
        Deque<Coordinate> queue = new ArrayDeque<>();
        Map<Coordinate, Coordinate> relations = new HashMap<>();
        relations.put(current, null);
        queue.push(current);
        while (!queue.isEmpty()) {
            current = queue.poll();
            path.add(current);
            if (current.equals(end)) {
                break;
            }
            visited[current.y()][current.x()] = true;
            List<Cell> neighbours = getValidNeighbours(new Cell(current.y(), current.x()), cells);
            for (Cell neighbour : neighbours) {
                Coordinate neighbourCoordinate = neighbour.getCoordinate();
                if (!visited[neighbourCoordinate.y()][neighbourCoordinate.x()]) {
                    queue.push(neighbourCoordinate);
                    relations.put(neighbourCoordinate, current);
                }
            }
        }
        return createPath(relations, current);

    }
}
