package edu.hw8.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SimpleGeneratorTest {

    @Test
    void find() {
        Generator generator = new SimpleGenerator();
        String actual = generator.find("202cb962ac59075b964b07152d234b70", 3);
        Assertions.assertEquals("123", actual);
    }

    @Test
    void findAll() {
        Generator generator = new SimpleGenerator();
        Map<String, String> values = Map.of("a.v.petrov", "49f68a5c8493ec2c0bf489821c21fc3b",
            "v.v.belov", "caf1a3dfb505ffed0d024130f58c5cfa"
        );
        Map<String, String> actual = generator.findAll(values, 3);
        Assertions.assertEquals("hi", actual.get("a.v.petrov"));
        Assertions.assertEquals("321", actual.get("v.v.belov"));
    }

    @Test
    void findEmpty() {
        Generator generator = new SimpleGenerator();
        String actual = generator.find("202cb962ac59075b964b07152d234b70", 2);
        Assertions.assertEquals(null, actual);
    }
}
