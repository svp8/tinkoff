package edu.project3.stats;

import edu.project3.Format;
import edu.project3.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopRequestTypeStatistic implements Statistic {

    public static final int MAX_SIZE = 3;

    @Override
    public List<String> compute(List<Log> logs, Format format) {
        Map<String, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::requestType,
                Collectors.counting()
            ));
        List<Map.Entry<String, Long>> sortedTop =
            stats.entrySet().stream().sorted((x, y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = new ArrayList<>();
        for (int i = 0; i < sortedTop.size(); i++) {
            List<String> line = new ArrayList<>(2);
            String type = sortedTop.get(i).getKey();
            Long count = sortedTop.get(i).getValue();
            line.add(type);
            line.add(String.valueOf(count));
            lines.add(line);
        }
        List<String> columnNames = List.of("Тип", "Количество");
        String title = "Типы запроса";
        return Statistic.createTable(lines, columnNames, title, format);
    }
}
