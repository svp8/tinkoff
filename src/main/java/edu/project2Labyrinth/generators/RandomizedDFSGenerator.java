package edu.project2Labyrinth.generators;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class RandomizedDFSGenerator implements Generator {
    Random random;

    public RandomizedDFSGenerator(Random random) {
        this.random = random;
    }

    private Cell randomUnvisitedNeighbour(Cell cell, List<Cell> grid) {
        List<Cell> neighbours =
            Labyrinth.neighbourList(cell, grid).stream().filter(cell1 -> !cell1.isVisited()).toList();
        if (neighbours.size() == 1) {
            return neighbours.get(0);
        }
        if (neighbours.size() > 0) {
            return neighbours.get(random.nextInt(neighbours.size()));
        } else {
            return null;
        }
    }

    @Override
    public Labyrinth generate(int height, int width) {
        Deque<Cell> stack = new ArrayDeque<>();
        List<Cell> grid = init(height, width);
        Cell current = grid.get(0);
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.peekFirst();
            current.setVisited(true);
            Cell next = randomUnvisitedNeighbour(current, grid);
            if (next != null) {
                stack.addFirst(next);
                Labyrinth.removeWalls(current, next);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        return new Labyrinth(height, width, grid);
    }
}
