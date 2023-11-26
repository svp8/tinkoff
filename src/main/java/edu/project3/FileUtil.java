package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class FileUtil {
    private FileUtil() {
    }

    public static Path createFileWithFormat(List<String> lines, Format format, Path root) {
        Path file;

        if (format.equals(Format.MARKDOWN)) {
            file = Path.of(root.toString(), "statistic.md");
        } else {
            file = Path.of(root.toString(), "statistic.adoc");
        }
        try {
            if (!file.toFile().exists()) {
                Files.createFile(file);
            }
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file.toFile(), false))) {
                for (String line : lines) {
                    fileWriter.write(line);
                    fileWriter.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
