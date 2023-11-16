package edu.project3.stats;

import edu.project3.Format;
import edu.project3.Log;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public interface Statistic {
    List<String> compute(List<Log> logs, Format format);

    static List<String> createTable(List<List<String>> lines, List<String> columnNames, String title, Format format) {
        if (format.equals(Format.MARKDOWN)) {
            return createTableMD(lines, columnNames, title);
        } else {
            return createTableADOC(lines, columnNames, title);
        }
    }

    static List<String> createTableMD(List<List<String>> lines, List<String> columnNames, String title) {
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
        return convertedLines;
    }

    static List<String> createTableADOC(List<List<String>> lines, List<String> columnNames, String title) {
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
        return convertedLines;
    }
}
