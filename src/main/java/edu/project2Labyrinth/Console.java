package edu.project2Labyrinth;

import edu.project2Labyrinth.generators.Generator;
import edu.project2Labyrinth.generators.HuntAndKillGenerator;
import edu.project2Labyrinth.generators.RandomizedDFSGenerator;
import edu.project2Labyrinth.solvers.BFSSolver;
import edu.project2Labyrinth.solvers.DFSSolver;
import edu.project2Labyrinth.solvers.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"uncommentedmain", "RegexpSinglelineJava"})
public class Console {
    private final static Logger LOGGER = LogManager.getLogger();
    public static final String REGEX_COORDINATE = "^\\d+$";
    public static final String REGEX_DIMENSIONS = "^((?!(0))[0-9]*)$";
    public static final int LEFT = 3;
    public static final int RIGHT = 1;
    public static final int TOP = 0;

    public static void main(String[] args) {
        try {
            Console console = new Console();
            console.start();
        } catch (IOException e) {
            LOGGER.warn(e);
        }
    }

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        String[] input = null;
        while (flag) {
            LOGGER.info("Write height and width");
            input = bufferedReader.readLine().split(" ");
            if (validateDimensionsInput(input)) {
                flag = false;
            }
        }
        int height = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);
        flag = true;
        Generator generator;
        Labyrinth labyrinth = null;
        while (flag) {
            LOGGER.info("Choose labyrinth generator");
            LOGGER.info("1. RandomizedDFSGenerator");
            LOGGER.info("2. HuntAndKillGenerator");
            String inputGenerator = bufferedReader.readLine();
            generator = chooseGenerator(inputGenerator);
            if (generator == null) {
                LOGGER.warn("Wrong input number");
            } else {
                labyrinth = generator.generate(height, width);
                printLabyrinth(labyrinth, new ArrayList<>());
                flag = false;
            }
        }
        Coordinate start = null;
        flag = true;
        while (flag) {
            LOGGER.info("Write start coordinate x and y");
            input = bufferedReader.readLine().split(" ");
            if (validateCoordinateInput(input, height, width)) {
                flag = false;
                start = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[0]));
            }

        }
        Coordinate end = null;
        flag = true;
        while (flag) {
            LOGGER.info("Write end coordinate x and y");
            input = bufferedReader.readLine().split(" ");
            if (validateCoordinateInput(input, height, width)) {
                flag = false;
                end = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[0]));
            }

        }
        flag = true;
        Solver solver;
        while (flag) {
            LOGGER.info("Choose labyrinth solver");
            LOGGER.info("1. DFSSolver");
            LOGGER.info("2. BFSSolver");
            String solverChoice = bufferedReader.readLine();
            solver = chooseSolver(solverChoice);
            if (solver != null) {
                flag = false;
                List<Coordinate> path = solver.solve(labyrinth, start, end);
                printLabyrinth(labyrinth, path);
            } else {
                LOGGER.warn("Wrong input");
            }
        }
        bufferedReader.close();
    }

    public static Generator chooseGenerator(String input) {
        return switch (input) {
            case "1":
                yield new RandomizedDFSGenerator(new Random());
            case "2":
                yield new HuntAndKillGenerator(new Random());
            default:
                yield null;
        };
    }

    public static Solver chooseSolver(String solverChoice) {
        return switch (solverChoice) {
            case "1":
                yield new DFSSolver();
            case "2":
                yield new BFSSolver();
            default:
                yield null;
        };
    }

    public static boolean validateCoordinateInput(String[] input, int height, int width) {
        if (input.length != 2) {
            LOGGER.warn("Coordinate consists of 2 numbers");
            return false;
        } else if (input[0].matches(REGEX_COORDINATE) && input[1].matches(REGEX_COORDINATE)) {
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            if (x < width && x >= 0 && y < height && y >= 0) {
                return true;
            } else {
                LOGGER.warn("Coordinate out of bounds");
                return false;
            }
        } else {
            LOGGER.warn("Coordinate contains illegal arguments");
            return false;
        }
    }

    public static boolean validateDimensionsInput(String[] input) {
        if (input.length != 2) {
            LOGGER.warn("Dimensions consist of 2 numbers");
            return false;
        } else if (input[0].matches(REGEX_DIMENSIONS) && input[1].matches(REGEX_DIMENSIONS)) {
            return true;
        } else {
            LOGGER.warn("Dimensions contains illegal arguments");
            return false;
        }
    }

    public static void printLabyrinth(Labyrinth labyrinth, List<Coordinate> path) {
        int height = labyrinth.height();
        int width = labyrinth.width();
        List<Cell> cells = labyrinth.cells();
        for (int i = 0; i < height; i++) {
            boolean[] ceiling = new boolean[width];
            boolean[] left = new boolean[width];
            boolean[] right = new boolean[width];
            int k = 0;
            for (int j = i * width; j < i * width + width; j++) {
                boolean[] walls = cells.get(j).getWalls();
                ceiling[k] = walls[TOP];
                left[k] = walls[LEFT];
                right[k] = walls[RIGHT];
                k++;
            }
            System.out.print("+");
            for (int j = 0; j < width; j++) {
                if (ceiling[j]) {
                    System.out.print("---+");
                } else {
                    System.out.print("   +");
                }
            }
            System.out.println();
            if (left[0]) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < width; j++) {
                if (path.contains(new Coordinate(i, j))) {
                    System.out.print(" . ");
                } else {
                    System.out.print("   ");
                }
                if (right[j]) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        for (int j = 0; j < width; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

}
