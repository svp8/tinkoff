package edu.project3.finders;

import edu.project3.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryFinderTest {
    Path root = Path.of("src/main/resources");

    @Test
    void find() {
        Map<String, List<String>> map = new DirectoryFinder().find("logs/**/*", root);
        Assertions.assertTrue(map.containsKey("logs-2021.txt"));
    }

    @Test
    @DisplayName("Test should return multiple files")
    void getFilesMultipleTest() throws IOException, InterruptedException {
        Map<String, List<String>> map = new DirectoryFinder().find("logs/*", root);
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("2022"));
        Assertions.assertTrue(map.containsKey("2023-12"));
    }
}
