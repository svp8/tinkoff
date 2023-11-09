package edu.hw5.task1;

import edu.hw5.DiskMap;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {
    @Test
    void put() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw5/task1/map.txt"));


    }
}
