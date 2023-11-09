package edu.project2Labyrinth.generators;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayList;
import java.util.List;

public interface Generator {
    Labyrinth generate(int height, int width);

    static List<Cell> init(int height, int width) {
        List<Cell> grid = new ArrayList<>(height * width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid.add(new Cell(i, j));
            }
        }
        return grid;
    }
}
