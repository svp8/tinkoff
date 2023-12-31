package edu.project3.stats;

import edu.project3.Log;
import edu.project3.table.TableCreator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopResponseCodeStatistic implements Statistic {

    public static final int MAX_SIZE = 3;
    public static final int INITIAL_CAPACITY = 3;

    @Override
    public List<String> compute(List<Log> logs, TableCreator tableCreator) {
        Map<Integer, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::status,
                Collectors.counting()
            ));

        List<Map.Entry<Integer, Long>> sortedTop =
            stats.entrySet().stream().sorted((x, y) -> Long.compare(y.getValue(), x.getValue()))
                .limit(MAX_SIZE).toList();
        List<List<String>> lines = new ArrayList<>();
        for (int i = 0; i < sortedTop.size(); i++) {
            List<String> line = new ArrayList<>(INITIAL_CAPACITY);
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

        return tableCreator.createTable(lines, columnNames, title);
    }
}
