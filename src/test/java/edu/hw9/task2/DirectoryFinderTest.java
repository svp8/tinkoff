package edu.hw9.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryFinderTest {

    @Test
    void compute() throws IOException {
        Path root = Path.of("src/main/resources/dir").toAbsolutePath();
        Finder finder = new DirectoryFinder(root, 1);
        List<Path> actual = finder.find();
        Assertions.assertEquals(2, actual.size());
        for (Path path : actual) {
            try (DirectoryStream<Path>
                     stream = Files.newDirectoryStream(path, Files::isRegularFile)) {
                int count = 0;
                for (Path entry : stream) {
                    count++;
                }
                Assertions.assertTrue(count > 1);
            }
        }

    }
}
