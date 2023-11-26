package edu.project3.finders;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryFinder implements Finder {
    @Override
    public Map<String, List<String>> find(String path, Path root) {
        try {
            List<Path> paths = findWithPattern(root, path);
            HashMap<String, List<String>> files = new HashMap<>();
            for (Path value : paths) {
                files.put(value.getFileName().toString(), Files.readAllLines(value));
            }
            return files;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Path> findWithPattern(Path srcPath, String matcherPattern) throws IOException {
        final List<Path> filePaths = new ArrayList<>();
        final PathMatcher matcher =
            FileSystems.getDefault()
                .getPathMatcher("glob:" + srcPath.toString().replaceAll("\\\\", "/") + "/" + matcherPattern);
        Files.walkFileTree(srcPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(file)) {
                    filePaths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return filePaths;
    }
}
