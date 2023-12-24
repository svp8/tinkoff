package edu.hw9.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileByExtensionFinderTest {

    @Test
    void computeEmptyExtension() throws IOException {
        Path root = Path.of("src/main/resources/extensionDir").toAbsolutePath();
        Finder finder = new FileByExtensionFinder(root, "");
        List<Path> actual = finder.find();
        Assertions.assertEquals(1, actual.size());
        Assertions.assertTrue(actual.get(0).endsWith("a"));
    }

    @Test
    void computeTxt() throws IOException {
        Path root = Path.of("src/main/resources/extensionDir").toAbsolutePath();
        Finder finder = new FileByExtensionFinder(root, "txt");
        List<Path> actual = finder.find();
        Assertions.assertEquals(2, actual.size());
    }
}
