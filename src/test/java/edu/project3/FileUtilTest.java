package edu.project3;

import org.apache.tools.ant.DirectoryScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {
    Path root = Path.of("src/main/resources");

    @Test
    void createFileWithFormatADOC() throws IOException {
        Path path = FileUtil.createFileWithFormat(List.of("123", "456"), Format.ADOC, root);
        Assertions.assertTrue(Files.exists(path));
        List<String> lines = Files.readAllLines(path);
        Assertions.assertEquals("123", lines.get(0));
        Assertions.assertEquals("456", lines.get(1));
    }

    @Test
    void createFileWithFormatMARDOWN() throws IOException {
        Path path = FileUtil.createFileWithFormat(List.of("123", "456"), Format.MARKDOWN, root);
        Assertions.assertTrue(Files.exists(path));
        List<String> lines = Files.readAllLines(path);
        Assertions.assertEquals("123", lines.get(0));
        Assertions.assertEquals("456", lines.get(1));
    }
}
