package edu.project3.table;

import edu.project3.Format;
import java.util.List;

public interface TableCreator {
    List<String> createTable(List<List<String>> lines, List<String> columnNames, String title);

    static TableCreator getCreator(Format format) {
        if (format.equals(Format.MARKDOWN)) {
            return new MDTableCreator();
        } else {
            return new ADOCTableCreator();
        }
    }
}
