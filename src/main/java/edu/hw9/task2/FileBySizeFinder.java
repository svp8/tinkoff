package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class FileBySizeFinder extends RecursiveTask<List<Path>> implements Finder {
    private final Path root;
    private final int size;

    public FileBySizeFinder(Path root, int size) {
        this.root = root;
        this.size = size;
    }

    public List<Path> find() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FileBySizeFinder finder = new FileBySizeFinder(root, size);
            forkJoinPool.execute(finder);
            List<Path> results = finder.join();
            return results;
        }
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = Files::isDirectory;
        List<FileBySizeFinder> subTasks = new ArrayList<>();
        try (DirectoryStream<Path>
                 stream = Files.newDirectoryStream(root, filter)) {
            for (Path entry : stream) {
                FileBySizeFinder finder = new FileBySizeFinder(entry, size);
                finder.fork();
                subTasks.add(finder);
            }
        } catch (IOException ignored) {
        }
        try (Stream<Path> files = Files.list(root)
            .filter((f) -> Files.isRegularFile(f) && f.toFile().length() >= size)) {
            files.forEach(result::add);
        } catch (IOException ignored) {
        }

        for (FileBySizeFinder d : subTasks) {
            List<Path> temp = d.join();
            result.addAll(temp);
        }
        return result;
    }
}
