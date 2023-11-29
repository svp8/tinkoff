package edu.project3.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class StatisticConverter {
    private StatisticConverter() {
    }

    public static List<List<String>> convertToLines(List<Map.Entry<String, Long>> stats) {
        List<List<String>> lines = new ArrayList<>();
        for (int i = 0; i < stats.size(); i++) {
            List<String> lineContent = new ArrayList<>(2);
            String key = stats.get(i).getKey();
            Long value = stats.get(i).getValue();
            lineContent.add(key);
            lineContent.add(String.valueOf(value));
            lines.add(lineContent);
        }
        return lines;
    }
}
