package edu.project3;

import edu.project3.stats.Statistic;
import edu.project3.stats.TopRequestCountStatistic;
import edu.project3.stats.TopRequestStatistic;
import edu.project3.stats.TopRequestTypeStatistic;
import edu.project3.stats.TopResponseCodeStatistic;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyser {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        String path;
        LocalDate from = null;
        LocalDate to = null;
        String format = "markdown";
        ArrayList<Log> logs = new ArrayList<>();
        List<String> filenames = new ArrayList<>();
        int logCount = 0;
        if (args.length % 2 != 0) {
            return;
        }
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "--path":
                    path = args[i + 1];
                    try {
                        Map<String, List<String>> files = getFiles(path);
                        files.forEach((key, value) -> {
                            filenames.add(key);
                        });
                        List<String> lines = new ArrayList<>();
                        files.forEach((key, value) -> {
                            lines.addAll(value);
                        });
                        logCount = lines.size();
                        logs = convertToLog(lines);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "--from":
                    from = LocalDate.parse(args[i + 1]);
                    break;
                case "--to":
                    to = LocalDate.parse(args[i + 1]);
                    break;
                case "--format":
                    format = args[i + 1];
                    break;
            }
        }
        if (!logs.isEmpty()) {
            LocalDate finalTo = to;
            LocalDate finalFrom = from;
            List<Log> boundedLogs = logs.stream()
                .filter(log -> log.offsetDateTime().isAfter(OffsetDateTime.from(finalFrom)) &&
                    log.offsetDateTime().isBefore(
                        OffsetDateTime.from(finalTo))).toList();
            List<Statistic> statistics = List.of(new TopRequestStatistic(),
                new TopRequestCountStatistic(),
                new TopRequestTypeStatistic(),
                new TopResponseCodeStatistic());
            //Все строчки для статистики
            List<String> lines=new ArrayList<>();
            statistics.forEach(x->lines.addAll(x.compute(boundedLogs)));

        }

    }

    public static void topRequests() {

    }

    public static Map<String, List<String>> getFiles(String path) throws IOException, InterruptedException {
        if (path.startsWith("https://")) {
            URI uri = null;
            try {
                uri = new URI(path);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            HttpClient httpClient = HttpClient.newHttpClient();
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
        } else {
            List<String> lines = new ArrayList<>();
            List<Path> paths = findMe(Path.of(Paths.get("")
                .toAbsolutePath()
                .toString(), "src/main/java/edu/project3"), "glob:**/" + path);
            HashMap<String, List<String>> files = new HashMap<>();

            paths.forEach((p) -> {
                try {
                    files.put(p.getFileName().toString(), Files.readAllLines(p));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return files;

        }
    }

    public static ArrayList<Log> convertToLog(List<String> lines) {
        ArrayList<Log> logs = new ArrayList<>();
        Pattern pattern = Pattern.compile("(.*) - (.*) \\[(.*)\\] \"((\\w*) (.*))\" (\\d*) (\\d*) \"(.*)\" \"(.*)\"");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                String remoteAddress = matcher.group(1);
                String remoteUser = matcher.group(2);
                DateTimeFormatter f = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MMM/yyyy:HH:mm:ss ZZZ")
                    .toFormatter(Locale.ENGLISH);
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(matcher.group(3), f);
                String requestType = matcher.group(5);
                String request = matcher.group(6);
                int status = Integer.parseInt(matcher.group(7));
                int bytesSend = Integer.parseInt(matcher.group(8));
                String httpReferer = matcher.group(9);
                String httpUserAgent = matcher.group(10);
                Log log = new Log(
                    remoteAddress,
                    remoteUser,
                    offsetDateTime,
                    request,
                    requestType,
                    status,
                    bytesSend,
                    httpReferer,
                    httpUserAgent
                );
                logs.add(log);
            }
        }

        return logs;
    }

    public static void analyse(Path path) {

    }

    private static List<Path> findMe(Path srcPath, String matcherPattern) throws IOException {
        final List<Path> filePaths = new ArrayList<>();
        final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(matcherPattern);
        Files.walkFileTree(srcPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (matcher.matches(file)) {
                    filePaths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return filePaths;
    }

}
