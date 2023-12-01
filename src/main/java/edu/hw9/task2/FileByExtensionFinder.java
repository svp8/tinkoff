package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class FileByExtensionFinder extends RecursiveTask<List<Path>> implements Finder {
    private final Path root;
    private final String extension;

    public FileByExtensionFinder(Path root, String extension) {
        this.root = root;
        this.extension = extension;
    }

    public List<Path> find() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FileByExtensionFinder finder = new FileByExtensionFinder(root, extension);
            forkJoinPool.execute(finder);
            List<Path> results = finder.join();
            return results;
        }
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        DirectoryStream.Filter<Path> filter = Files::isDirectory;
        List<FileByExtensionFinder> subTasks = new ArrayList<>();
        try (DirectoryStream<Path>
                 stream = Files.newDirectoryStream(root, filter)) {
            for (Path entry : stream) {
                FileByExtensionFinder finder = new FileByExtensionFinder(entry, extension);
                finder.fork();
                subTasks.add(finder);
            }
        } catch (IOException ignored) {
        }

        if (extension == null || extension.isBlank()) {
            try (Stream<Path> files = Files.list(root)
                .filter((f) -> Files.isRegularFile(f) && !f.getFileName().toString().contains("."))) {
                files.forEach(result::add);
            } catch (IOException ignored) {
            }
        } else {
            String glob = "glob:**." + extension;
            final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
            try (Stream<Path> files = Files.list(root).filter((f) -> Files.isRegularFile(f) && matcher.matches(f))) {
                files.forEach(result::add);
            } catch (IOException ignored) {
            }
        }

        for (FileByExtensionFinder d : subTasks) {
            List<Path> temp = d.join();
            result.addAll(temp);
        }
        return result;
    }
}
