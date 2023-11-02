package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
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
        Solver solver = new DFSSolver();
        List<Coordinate> path = solver.solve(labyrinth, new Coordinate(0, 0), new Coordinate(2, 2));
        Assertions.assertEquals(new Coordinate(0, 0), path.get(0));
        Assertions.assertEquals(new Coordinate(2, 2), path.get(path.size() - 1));
        Assertions.assertEquals(5, path.size());
    }
}
