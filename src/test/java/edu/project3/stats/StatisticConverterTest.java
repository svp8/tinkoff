package edu.project3.stats;

import edu.project3.LogParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class StatisticConverterTest {

    @Test
    void convertToLines() {
        List<Map.Entry<String, Long>> stats = List.of(Map.entry("abc", (long) 123), Map.entry("d", (long) 12));

        List<List<String>> lines = StatisticConverter.convertToLines(stats);
        Assertions.assertEquals(List.of("abc", "123"), lines.get(0));
        Assertions.assertEquals(List.of("d", "12"), lines.get(1));
    }
}
