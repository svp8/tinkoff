package edu.project2Labyrinth.generators;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Console;
import edu.project2Labyrinth.Labyrinth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RandomizedDFSGeneratorTest {

    @Test
    @DisplayName("RandomizedDfs - Test generated labyrinth ")
    void generate() {
        RandomizedDFSGenerator generator = new RandomizedDFSGenerator(new Random(123));
        Labyrinth labyrinth = generator.generate(3, 3);
        Console.printLabyrinth(labyrinth, new ArrayList<>());
        List<Cell> cells = labyrinth.cells();
        Cell firstCell = cells.get(0);
        Cell secondCell = cells.get(1);
        assertArrayEquals(new boolean[] {true, true, false, true}, firstCell.getWalls());
        assertArrayEquals(new boolean[] {true, false, true, true}, secondCell.getWalls());
    }
}
