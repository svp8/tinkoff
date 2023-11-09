package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import edu.project2Labyrinth.generators.Generator;
import edu.project2Labyrinth.generators.HuntAndKillGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DFSSolverTest {

    @Test
    void solve() {
        HuntAndKillGenerator generator = new HuntAndKillGenerator(new Random(123));
        Labyrinth labyrinth = generator.generate(3, 3);
        List<Coordinate> expected = List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(1, 1),
            new Coordinate(2, 1),
            new Coordinate(2, 2)
        );
        Solver solver = new DFSSolver();
        List<Coordinate> path = solver.solve(labyrinth, new Coordinate(0, 0), new Coordinate(2, 2));
        Assertions.assertEquals(5, path.size());
        Assertions.assertEquals(expected, path);

    }

    @Test
    void negativeSolve() {
        List<Cell> grid = Generator.init(1, 2);
        Labyrinth labyrinth = new Labyrinth(1, 2, grid);
        Solver solver = new DFSSolver();
        List<Coordinate> path = solver.solve(labyrinth, new Coordinate(0, 0), new Coordinate(0, 1));
        Assertions.assertNull(path);
    }
}
