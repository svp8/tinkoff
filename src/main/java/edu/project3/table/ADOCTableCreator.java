package edu.project3.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class ADOCTableCreator implements TableCreator {
    @Override
    public List<String> createTable(List<List<String>> lines, List<String> columnNames, String title) {
        List<String> convertedLines = new ArrayList<>();
        convertedLines.add("*" + title + "*");
        convertedLines.add("");
        convertedLines.add("|===");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < columnNames.size(); i++) {
            String name = columnNames.get(i);
            stringBuilder.append("|");
            stringBuilder.append(name);
            stringBuilder.append(" ");
        }
        convertedLines.add(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        convertedLines.add("");
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            for (String content : line) {
                stringBuilder.append("|");
                stringBuilder.append(content);
                stringBuilder.append(" ");
            }
            convertedLines.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        convertedLines.add("|===");
        return Collections.unmodifiableList(convertedLines);
    }
}
