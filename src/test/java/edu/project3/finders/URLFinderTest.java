package edu.project3.finders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class URLFinderTest {

    @Test
    void find() {
        Map<String, List<String>> map = new URLFinder().find(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            null
        );
        System.out.println(map.keySet());
        Assertions.assertTrue(map.containsKey("/elastic/examples/master/Common Data Formats/nginx_logs/nginx_logs"));
    }
}
