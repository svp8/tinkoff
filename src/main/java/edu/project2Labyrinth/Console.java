package edu.project2Labyrinth;

import edu.project2Labyrinth.generators.RandomizedDFSGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Console {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//            LOGGER.info("Choose labyrinth generator");
//            LOGGER.info("1. ");
//            LOGGER.info("2. ");
            String[] input = bufferedReader.readLine().split(" ");
            int height=Integer.parseInt(input[0]);
            int width=Integer.parseInt(input[1]);
            Labyrinth labyrinth = new RandomizedDFSGenerator().generate(height, width);
            List<Cell> cells = labyrinth.getCells();
            for (int i = 0; i < width * height; i += width) {
                boolean[] ceiling = new boolean[width];
                boolean[] left = new boolean[width];
                boolean[] right = new boolean[width];
                int k = 0;
                for (int j = i; j < i + width; j++) {
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
                }else System.out.print(" ");
                for (int j = 0; j < width; j++) {
                    if (right[j]) {
                        System.out.print("   |");
                    } else {
                        System.out.print("    ");
                    }
                }
                System.out.println();
            }
            for (int j = 0; j < width; j++) {

                    System.out.print("+---");
            }
            System.out.print("+");
        } catch (IOException e) {
            LOGGER.warn(e);
        }

    }

}
