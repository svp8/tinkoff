package edu.project3.stats;

import edu.project3.Format;
import edu.project3.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopResponseCodeStatistic implements Statistic {

    public static final int MAX_SIZE = 3;

    @Override
    public List<String> compute(List<Log> logs, Format format) {
        Map<Integer, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::status,
                Collectors.counting()
            ));

        List<Map.Entry<Integer, Long>> sortedTop =
            stats.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue)).toList().reversed().stream()
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = new ArrayList<>();
        for (int i = 0; i < sortedTop.size(); i++) {
            List<String> line = new ArrayList<>();
            Integer code = sortedTop.get(i).getKey();
            Long count = sortedTop.get(i).getValue();
            line.add(String.valueOf(code));
            String name = null;
            try {
                name = HttpStatusCode.getByValue(code).getDescription();
            } catch (IllegalArgumentException e) {
                name = "Unknown code";
            }
            line.add(name);
            line.add(String.valueOf(count));
            lines.add(line);
        }
        List<String> columnNames = List.of("Код", "Имя", "Количество");
        String title = "Коды ответа";

        return Statistic.createTable(lines, columnNames, title, format);
    }
}
