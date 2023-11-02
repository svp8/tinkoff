package edu.project2Labyrinth.generators;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Labyrinth;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class HuntAndKillGenerator implements Generator {
    Random random;

    public HuntAndKillGenerator(Random random) {
        this.random = random;
    }

    @Override
    public Labyrinth generate(int height, int width) {
        List<Cell> grid = init(height, width);
        Cell current = grid.get(0);
        boolean[][] visited = new boolean[height][width];
        boolean flag = true;
        while (flag) {
            visited[current.getCoordinate().y()][current.getCoordinate().x()] = true;
            List<Cell> neighbours = getUnvisitedNeighboursList(current, grid, visited);
            Cell next = null;
            if (neighbours.size() == 1) {
                next = neighbours.get(0);
            } else if (neighbours.size() > 0) {
                next = neighbours.get(random.nextInt(neighbours.size()));
            }
            if (next != null) {
                Labyrinth.removeWalls(current, next);
                current = next;
            } else {
                // hunt
                Optional<Cell>
                    opt = grid.parallelStream()
                    .filter(c -> visited[c.getCoordinate().y()][c.getCoordinate().x()]
                        && getUnvisitedNeighboursList(c, grid, visited).size() > 0)
                    .findAny();
                if (opt.isPresent()) {
                    current = opt.get();
                } else {
                    flag = false;
                }
            }
        }
        return new Labyrinth(height, width, grid);
    }

    public List<Cell> getUnvisitedNeighboursList(Cell cell, List<Cell> grid, boolean[][] visited) {
        return Labyrinth.neighbourList(cell, grid)
            .stream()
            .filter(c -> !visited[c.getCoordinate().y()][c.getCoordinate().x()]).toList();
    }
}
