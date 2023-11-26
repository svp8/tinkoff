package edu.project3.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class MDTableCreator implements TableCreator {
    @Override
    public List<String> createTable(List<List<String>> lines, List<String> columnNames, String title) {
        List<String> convertedLines = new ArrayList<>();
        convertedLines.add("#### " + title);
        convertedLines.add("");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ");
        for (int i = 0; i < columnNames.size(); i++) {
            String name = columnNames.get(i);
            stringBuilder.append(name);
            stringBuilder.append(" | ");
        }
        convertedLines.add(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append("|");
        for (int i = 0; i < columnNames.size(); i++) {
            stringBuilder.append(":-:|");
        }
        convertedLines.add(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            stringBuilder.append("| ");
            for (String content : line) {
                stringBuilder.append(content);
                stringBuilder.append(" | ");
            }
            convertedLines.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        return Collections.unmodifiableList(convertedLines);
    }
}
