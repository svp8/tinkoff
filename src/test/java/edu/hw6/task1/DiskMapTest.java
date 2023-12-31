package edu.hw6.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
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
    @Test
    void getEntrySet() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt"));
        diskMap.put("123","privet1");
        diskMap.put("1234","privet2");
        Set<Map.Entry<String,String>> entrySet=diskMap.entrySet();
        assertEquals(2,entrySet.size());
        assertTrue(entrySet.contains(Map.entry("123","privet1")));
        assertTrue(entrySet.contains(Map.entry("1234","privet2")));

    }

    @Test
    void remove() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt"));
        diskMap.put("123","privet123");
        diskMap.put("1","privet1");
        diskMap.put("2","privet2");
        String value=diskMap.remove("1");
        Set<Map.Entry<String,String>> entrySet=diskMap.entrySet();
        assertEquals(2,diskMap.size());
        assertEquals("privet1",value);
        assertTrue(entrySet.contains(Map.entry("123","privet123")));
        assertTrue(entrySet.contains(Map.entry("2","privet2")));
    }
    @Test
    void clear() throws IOException {
        DiskMap diskMap=new DiskMap(Path.of(Paths.get("")
            .toAbsolutePath()
            .toString(),"/src/test/java/edu/hw6/task1/map.txt"));
        diskMap.put("123","privet123");
        diskMap.put("1","privet1");
        diskMap.put("2","privet2");
        diskMap.clear();
        assertEquals(0,diskMap.size());
    }
}
