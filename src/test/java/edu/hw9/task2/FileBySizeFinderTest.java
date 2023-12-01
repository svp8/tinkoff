package edu.hw9.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileBySizeFinderTest {

    @Test
    void compute() throws IOException {
        Path root = Path.of("src/main/resources/dir").toAbsolutePath();
        List<Path> actual = FileBySizeFinder.find(root, 1);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertTrue(actual.get(0).endsWith("123"));
    }
    @Test
    void computeMoreThanNegative() throws IOException {
        Path root = Path.of("src/main/resources/dir").toAbsolutePath();
        List<Path> actual = FileBySizeFinder.find(root, 0);
        Assertions.assertEquals(6, actual.size());

    }
}
