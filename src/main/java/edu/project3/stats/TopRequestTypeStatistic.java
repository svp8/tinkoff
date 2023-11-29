package edu.project3.stats;

import edu.project3.Log;
import edu.project3.table.TableCreator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopRequestTypeStatistic implements Statistic {

    public static final int MAX_SIZE = 3;

    @Override
    public List<String> compute(List<Log> logs, TableCreator tableCreator) {
        Map<String, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::requestType,
                Collectors.counting()
            ));
        List<Map.Entry<String, Long>> sortedTop =
            stats.entrySet().stream().sorted((x, y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = StatisticConverter.convertToLines(sortedTop);
        List<String> columnNames = List.of("Тип", "Количество");
        String title = "Типы запроса";
        return tableCreator.createTable(lines, columnNames, title);
    }
}
