package edu.hw6.task2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2Test {
    ArrayList<Path> paths = new ArrayList<>();
    Path root = Paths.get("");

    @AfterEach
    void after() {
        for (Path path : paths) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void cloneFile() throws IOException {
        Path path = Task2.cloneFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt"));
        paths.add(path);
        List<String> lines=Files.readAllLines(path);

        Assertions.assertEquals("123456789",lines.get(0));
        Assertions.assertEquals("abc",lines.get(1));
        Assertions.assertTrue(path.endsWith("Tinkoff Bank Biggest Secret — копия.txt"));
    }

    @Test
    void cloneMultipleFile() throws IOException {
        Path path = null;
        for (int i = 0; i < 2; i++) {
            path = Task2.cloneFile(Path.of(root
                .toAbsolutePath()
                .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt"));
            paths.add(path);
        }
        List<String> lines=Files.readAllLines(path);

        Assertions.assertTrue(path.endsWith("Tinkoff Bank Biggest Secret — копия (2).txt"));
        Assertions.assertEquals("123456789",lines.get(0));
        Assertions.assertEquals("abc",lines.get(1));
    }

    @Test
    @DisplayName("Test if clone with number exists and clone without does not")
    void cloneWithoutNumber() throws IOException {
        paths.add(Files.createFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (2).txt")));
        Path path = null;
        path = Task2.cloneFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt"));
        paths.add(path);
        List<String> lines=Files.readAllLines(path);

        Assertions.assertEquals("123456789",lines.get(0));
        Assertions.assertEquals("abc",lines.get(1));
        Assertions.assertTrue(path.endsWith("Tinkoff Bank Biggest Secret — копия.txt"));
    }

    @Test
    @DisplayName("Test clone with missing number")
    void cloneWithNumber() throws IOException {
        paths.add(Files.createFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (2).txt")));
        paths.add(Files.createFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия.txt")));
        paths.add(Files.createFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret — копия (4).txt")));
        Path path = null;
        path = Task2.cloneFile(Path.of(root
            .toAbsolutePath()
            .toString(), "/src/test/java/edu/hw6/task2/Tinkoff Bank Biggest Secret.txt"));
        paths.add(path);
        List<String> lines=Files.readAllLines(path);

        Assertions.assertEquals("123456789",lines.get(0));
        Assertions.assertEquals("abc",lines.get(1));
        Assertions.assertTrue(path.endsWith("Tinkoff Bank Biggest Secret — копия (3).txt"));
    }
}
