package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class ExtensionFilter implements AbstractFilter {
    private final String extension;

    public ExtensionFilter(@NotNull String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        String filename = entry.toString();
        Optional<String> ext = Optional.of(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1));
        return ext.map(s -> s.equals(extension)).orElseGet(extension::isBlank);
    }
}
