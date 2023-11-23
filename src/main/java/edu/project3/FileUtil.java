package edu.project3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FileUtil {
    private FileUtil() {
    }

    public static Map<String, List<String>> getFiles(String path, Path root) throws IOException, InterruptedException {
        //Название файла - строки файла
        if (path.startsWith("https://")) {
            URI uri = null;
            try {
                uri = new URI(path);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            try (HttpClient httpClient = HttpClient.newHttpClient()) {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                String[] arr = body.split("\n");
                HashMap<String, List<String>> files = new HashMap<>();
                files.put(request.uri().getPath(), Arrays.stream(arr).toList());
                return files;
            }
        } else {
            List<Path> paths = findWithPattern(root, path);
            HashMap<String, List<String>> files = new HashMap<>();
            for (Path value : paths) {
                files.put(value.getFileName().toString(), Files.readAllLines(value));
            }
            return files;

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
