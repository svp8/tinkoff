package edu.project2Labyrinth;

import edu.project2Labyrinth.generators.HuntAndKillGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {
    static Labyrinth labyrinth;

    @BeforeAll static void before() {
        labyrinth = new HuntAndKillGenerator(new Random(123)).generate(3, 3);
    }

    @Test
    void neighbourList() {
        List<Cell> neighbours = Labyrinth.neighbourList(new Cell(0, 0), labyrinth.cells());
        Assertions.assertEquals(
            List.of(new Coordinate(0, 1), new Coordinate(1, 0)),
            neighbours.stream().map(Cell::getCoordinate).toList()
        );
    }

    @Test
    void checkNeighbourInGridBounds() {
        List<Cell> grid = labyrinth.cells();
        Cell cellWithNegative = new Cell(-1, 0);
        Cell cellOutOfBounds = new Cell(3, 0);
        Cell cellValid = new Cell(2, 0);
        Assertions.assertNull(Labyrinth.checkNeighbourInGridBounds(grid, cellOutOfBounds));
        Assertions.assertNull(Labyrinth.checkNeighbourInGridBounds(grid, cellWithNegative));
        Assertions.assertNotNull(Labyrinth.checkNeighbourInGridBounds(grid, cellValid));
    }

    @Test
    @DisplayName("Should return list of neighbours without a wall between")
    void getValidNeighbours() {
        List<Cell> grid = labyrinth.cells();
        List<Cell> neighbours = Labyrinth.getValidNeighbours(new Cell(0, 0), grid);
        Assertions.assertEquals(1, neighbours.size());
        Assertions.assertEquals(new Coordinate(1, 0), neighbours.get(0).getCoordinate());
    }

    @Test
    void removeWalls() {
        Cell cell1 = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);

        Labyrinth.removeWalls(cell1, cell2);
        boolean[] walls1 = cell1.getWalls();
        boolean[] walls2 = cell2.getWalls();

        Assertions.assertArrayEquals(new boolean[] {true, false, true, true}, walls1);
        Assertions.assertArrayEquals(new boolean[] {true, true, true, false}, walls2);
    }
}
