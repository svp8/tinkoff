package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class RegexpFilter implements AbstractFilter {
    private final String regexp;

    public RegexpFilter(@NotNull String regexp) {
        this.regexp = regexp;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        String name = entry.getFileName().toString();
        return name.matches(regexp);
    }
}
