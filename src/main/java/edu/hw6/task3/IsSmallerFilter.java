package edu.hw6.task3;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsSmallerFilter implements AbstractFilter {
    long size;

    public IsSmallerFilter(long size) {
        this.size = size;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        FileChannel fileChannel;
        if (!Files.isRegularFile(entry)) {
            return false;
        }
        try {
            fileChannel = FileChannel.open(entry);
            long fileSize = fileChannel.size();
            fileChannel.close();
            if (fileSize < size) {
                return true;
            }
        } catch (IOException ignored) {
        }
        return false;
    }
}
