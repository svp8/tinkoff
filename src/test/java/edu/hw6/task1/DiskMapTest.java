package edu.hw6.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {

    @BeforeEach
    void deleteFile(){
        Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt").toFile().delete();
    }
    @Test
    void put() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt"));
        diskMap.put("123","privet1");
        diskMap.put("aaa","privet2");
        diskMap.put("bbb","privet3");
        assertEquals(3,diskMap.size());
        assertEquals("privet1",diskMap.get("123"));
        assertEquals("privet2",diskMap.get("aaa"));

    }
    @Test
    void putAgain() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt"));
        diskMap.put("123","privet1");
        diskMap.put("123","privet2");
        assertEquals(1,diskMap.size());
        assertEquals("privet2",diskMap.get("123"));

    }
}
