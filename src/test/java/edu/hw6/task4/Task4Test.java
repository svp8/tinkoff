package edu.hw6.task4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    Path root = Path.of(Paths.get("")
        .toAbsolutePath()
        .toString(), "/src/test/java/edu/hw6/task4");

    @AfterEach
    void deleteFile() throws IOException {
        Files.delete(Path.of(root.toString(), "file"));
    }

    @Test
    void writeFile() throws IOException {
        Path file = Path.of(root.toString(), "file");
        Task4.writeFile(file);
        List<String> lines = Files.readAllLines(file);
        Assertions.assertEquals(
            "Programming is learned by writing programs. ― Brian Kernighan",
            lines.get(0)
        );
    }
}
