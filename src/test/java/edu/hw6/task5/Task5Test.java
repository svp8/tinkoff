package edu.hw6.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    void hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        long[] actual=Task5.hackerNewsTopStories();
        Assertions.assertTrue(actual.length>0);
    }

    @Test
    void news() throws URISyntaxException, IOException, InterruptedException {
        String actual=Task5.news(37570037);
        Assertions.assertEquals("JDK 21 Release Notes",actual);
    }
}
