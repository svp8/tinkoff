package edu.project3.stats;

import edu.project3.Log;
import edu.project3.table.TableCreator;
import java.util.List;

public interface Statistic {
    List<String> compute(List<Log> logs, TableCreator tableCreator);

}
