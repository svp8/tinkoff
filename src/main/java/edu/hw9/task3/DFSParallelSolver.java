package edu.hw9.task3;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import edu.project2Labyrinth.solvers.Solver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import static edu.project2Labyrinth.Labyrinth.getValidNeighbours;

public class DFSParallelSolver extends RecursiveTask<List<Coordinate>> implements Solver {
    private Coordinate current;
    private Coordinate target;
    private boolean[][] visited;
    private List<Cell> cells;
    private Map<Coordinate, Coordinate> relations;

    public DFSParallelSolver(
        Coordinate current,
        Coordinate target,
        boolean[][] visited, List<Cell> cells,
        Map<Coordinate, Coordinate> relations
    ) {
        this.current = current;
        this.target = target;
        this.visited = visited;
        this.cells = cells;
        this.relations = relations;
    }

    public DFSParallelSolver() {

    }

    @Override
    public List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2) {
        List<Cell> c = labyrinth.cells();
        boolean[][] v = new boolean[labyrinth.height()][labyrinth.width()];
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            Map<Coordinate, Coordinate> temp = new HashMap<>();
            temp.put(current, null);
            DFSParallelSolver processor = new DFSParallelSolver(coordinate1, coordinate2, v, c, temp);

            forkJoinPool.execute(processor);

            List<Coordinate> results = processor.join();
            return results;
        }
    }

    @Override
    protected List<Coordinate> compute() {
        if (current.equals(target)) {
            return createPath(relations, current);
        }
        visited[current.y()][current.x()] = true;
        List<Cell> neighbours = getValidNeighbours(new Cell(current.y(), current.x()), cells);
        List<DFSParallelSolver> subTasks = new ArrayList<>();
        for (Cell neighbour : neighbours) {
            Coordinate neighbourCoordinate = neighbour.getCoordinate();
            if (!visited[neighbourCoordinate.y()][neighbourCoordinate.x()]) {
                relations.put(neighbourCoordinate, current);
                DFSParallelSolver dfsParallelSolver = new DFSParallelSolver(neighbourCoordinate,
                    target,
                    visited, cells,
                    relations
                );
                dfsParallelSolver.fork();
                subTasks.add(dfsParallelSolver);
            }
        }
        for (DFSParallelSolver task : subTasks) {
            List<Coordinate> temp = task.join();
            if (temp != null && temp.size() != 0 && temp.get(temp.size() - 1).equals(target)) {
                return temp;
            }
        }
        return null;
    }
}
