package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsWritableFilter implements AbstractFilter {
    @Override
    public boolean accept(Path entry) throws IOException {
        return Files.isWritable(entry);
    }
}
