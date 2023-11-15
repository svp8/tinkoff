package edu.project3.stats;

import edu.project3.Log;
import java.util.List;

public interface Statistic {
    List<String> compute(List<Log> logs);
}
