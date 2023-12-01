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

public class DirectoryFinder extends RecursiveTask<List<Path>> {
    private final Path root;
    private final int fileCount;

    public DirectoryFinder(Path root, int fileCount) {
        this.root = root;
        this.fileCount = fileCount;
    }

    public static List<Path> find(Path root, int fileCount) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectoryFinder directoryFinder = new DirectoryFinder(root, fileCount);
            forkJoinPool.execute(directoryFinder);
            List<Path> results = directoryFinder.join();
            return results;
        }
    }

    @Override
    protected List<Path> compute() {
        List<Path> dirs = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = Files::isDirectory;
        List<DirectoryFinder> subTasks = new ArrayList<>();
        long dirCount = 0;
        try (DirectoryStream<Path>
                 stream = Files.newDirectoryStream(root, filter)) {
            for (Path entry : stream) {
                DirectoryFinder directoryFinder = new DirectoryFinder(entry, fileCount);
                directoryFinder.fork();
                subTasks.add(directoryFinder);
                dirCount++;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        try (Stream<Path> files = Files.list(root)) {
            long count = files.count() - dirCount;
            if (count > fileCount) {
                dirs.add(root);
            }
        } catch (IOException ignored) {
        }

        for (DirectoryFinder d : subTasks) {
            List<Path> temp = d.join();
            dirs.addAll(temp);
        }
        return dirs;
    }
}
