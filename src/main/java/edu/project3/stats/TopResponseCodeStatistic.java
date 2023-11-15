package edu.project3.stats;

import edu.project3.Log;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopResponseCodeStatistic implements Statistic {
    @Override
    public List<String> compute(List<Log> logs) {
        Map<Integer, Long> stats =
            logs.stream().collect(Collectors.groupingBy(
                Log::status,
                Collectors.counting()
            ));
        List<Map.Entry<Integer, Long>> sortedTop =
            stats.entrySet().stream().limit(3).sorted(Comparator.comparingLong(Map.Entry::getValue)).toList();
        List<String> lines=new ArrayList<>();
        for(int i=sortedTop.size()-1;i>=0;i++){
            Integer code=sortedTop.get(i).getKey();
            Long count=sortedTop.get(i).getValue();
            lines.add(code+" "+count);
        }
        return lines;
    }
}
