package edu.project3.stats;

import edu.project3.Log;
import edu.project3.table.TableCreator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopRequestStatistic implements Statistic {

    public static final int MAX_SIZE = 3;

    @Override
    public List<String> compute(List<Log> logs, TableCreator tableCreator) {
        Map<String, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                (l) -> l.request().split(" ")[0],
                Collectors.counting()
            ));
        List<Map.Entry<String, Long>> sortedTop =
            stats.entrySet().stream().sorted((x, y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = StatisticConverter.convertToLines(sortedTop);
        List<String> columnNames = List.of("Ресурс", "Количество");
        String title = "Запрашиваемые ресурсы";
        return tableCreator.createTable(lines, columnNames, title);
    }
}
