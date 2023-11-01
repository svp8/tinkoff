package edu.project2Labyrinth;

import edu.project2Labyrinth.generators.RandomizedDFSGenerator;
import edu.project2Labyrinth.solvers.BFSSolver;
import edu.project2Labyrinth.solvers.DFSSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Console {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//            LOGGER.info("Choose labyrinth generator");
//            LOGGER.info("1. ");
//            LOGGER.info("2. ");
            LOGGER.info("Write height and width");
            String[] input = bufferedReader.readLine().split(" ");
            int height = Integer.parseInt(input[0]);
            int width = Integer.parseInt(input[1]);
            Labyrinth labyrinth = new RandomizedDFSGenerator().generate(height, width);
            printLabyrinth(labyrinth, new ArrayList<>());
//            LOGGER.info("Write start coordinate x and y");
//            input = bufferedReader.readLine().split(" ");
//            Coordinate start=new Coordinate(Integer.parseInt(input[1]),Integer.parseInt(input[0]));
//            LOGGER.info("Write end coordinate");
//            input = bufferedReader.readLine().split(" ");
//            Coordinate end=new Coordinate(Integer.parseInt(input[1]),Integer.parseInt(input[0]));
//            List<Coordinate> path=new DFSSolver().solve(labyrinth,start,end);
            List<Coordinate> path = new DFSSolver().solve(labyrinth, new Coordinate(0, 0), new Coordinate(5, 5));
            printLabyrinth(labyrinth, path);
            path = new BFSSolver().solve(labyrinth, new Coordinate(0, 0), new Coordinate(5, 5));
            printLabyrinth(labyrinth, path);
        } catch (IOException e) {
            LOGGER.warn(e);
        }

    }

    public static void printLabyrinth(Labyrinth labyrinth, List<Coordinate> path) {
        int height = labyrinth.getHeight();
        int width = labyrinth.getWidth();
        List<Cell> cells = labyrinth.getCells();
        for (int i = 0; i < height; i++) {
            boolean[] ceiling = new boolean[width];
            boolean[] left = new boolean[width];
            boolean[] right = new boolean[width];
            int k = 0;
            for (int j = i * width; j < i * width + width; j++) {
                boolean[] walls = cells.get(j).getWalls();
                ceiling[k] = walls[0];
                left[k] = walls[3];
                right[k] = walls[1];
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
