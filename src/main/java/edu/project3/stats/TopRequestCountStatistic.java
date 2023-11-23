package edu.project3.stats;

import edu.project3.Format;
import edu.project3.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopRequestCountStatistic implements Statistic {

    public static final int MAX_SIZE = 3;

    @Override
    public List<String> compute(List<Log> logs, Format format) {
        Map<String, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::remoteAddress,
                Collectors.counting()
            ));
        List<Map.Entry<String, Long>> sortedTop =
            stats.entrySet().stream().sorted((x, y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = new ArrayList<>();
        for (int i = 0; i < sortedTop.size(); i++) {
            List<String> lineContent = new ArrayList<>(2);
            String ip = sortedTop.get(i).getKey();
            Long count = sortedTop.get(i).getValue();
            lineContent.add(ip);
            lineContent.add(String.valueOf(count));
            lines.add(lineContent);
        }
        List<String> columnNames = List.of("Пользователь", "Количество запросов");
        String title = "Количество запросов по пользователю";
        return Statistic.createTable(lines, columnNames, title, format);
    }
}
