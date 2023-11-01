package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayList;
import java.util.List;
import static edu.project2Labyrinth.Labyrinth.checkNeighbourInGridBounds;
import static edu.project2Labyrinth.Labyrinth.getValidNeighbour;

public class DFSSolver implements Solver {
    ArrayList<Coordinate> result = new ArrayList<>();

    @Override
    public List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2) {
        List<Cell> cells = labyrinth.getCells();
        boolean[][] visited = new boolean[labyrinth.getHeight()][labyrinth.getWidth()];
        result.add(coordinate1);
        dfs(coordinate1, visited, new ArrayList<>(), coordinate2, cells);
        return result;
    }

    void dfs(Coordinate current, boolean[][] visited, List<Coordinate> path, Coordinate end, List<Cell> cells) {
        path.add(current);
        if (current.equals(end)) {
            result = new ArrayList<>(path);
            return;
        }
        visited[current.y()][current.x()] = true;
        List<Cell> neighbours = getValidNeighbour(new Cell(current.y(), current.x()), cells);
        for (Cell neighbour : neighbours) {
            Coordinate neighbourCoordinate = neighbour.getCoordinate();
            if (!visited[neighbourCoordinate.y()][neighbourCoordinate.x()]) {
                dfs(neighbourCoordinate, visited, path, end, cells);
                path.remove(path.size() - 1);
            }
        }
    }
}
