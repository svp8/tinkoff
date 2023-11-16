package edu.project3;

import edu.project3.stats.Statistic;
import edu.project3.stats.TopRequestCountStatistic;
import edu.project3.stats.TopRequestStatistic;
import edu.project3.stats.TopRequestTypeStatistic;
import edu.project3.stats.TopResponseCodeStatistic;
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
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UncommentedMain")
public class Analyser {
    public static final String EMPTY = "---";
    public static final int DATETIME = 3;
    public static final int REQUEST_TYPE = 5;
    public static final int REQUEST = 6;
    public static final int STATUS = 7;
    public static final int BYTES = 8;
    public static final int REFERER = 9;
    public static final int USER_AGENT = 10;
    LocalDate from;
    LocalDate to;
    Format format;
    List<Log> logs;
    List<String> filenames;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path ROOT = Path.of(Paths.get("")
        .toAbsolutePath()
        .toString(), "src/main/java/edu/project3");

    public Analyser(LocalDate from, LocalDate to, Format format, ArrayList<Log> logs, List<String> filenames) {
        this.from = from;
        this.to = to;
        this.format = format;
        this.logs = logs;
        this.filenames = filenames;
    }

    public static void main(String[] args) {
        Analyser analyser = getValuesFromConsole(args);
        analyser.analyse();
    }

    public static Analyser getValuesFromConsole(String[] args) {
        String path;
        LocalDate from = LocalDate.MIN;
        LocalDate to = LocalDate.MAX;
        Format format = Format.MARKDOWN;
        ArrayList<Log> logs = new ArrayList<>();
        List<String> filenames = new ArrayList<>();
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Wrong amount of arguments");
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
                    format = Format.valueOf(args[i + 1].toUpperCase());
                    break;
                default:
                    break;

            }
        }
        if (logs.isEmpty()) {
            throw new IllegalArgumentException("No logs provided");
        }

        return new Analyser(from, to, format, logs, filenames);
    }

    public List<String> createGeneralStatistic() {
        List<String> columnNames = List.of("Метрика", "Значение");
        String title = "Общая информация";
        List<List<String>> lines = new ArrayList<>();
        StringBuilder filenameString = new StringBuilder();
        for (String i : filenames) {
            filenameString.append(i);
            filenameString.append(" ");
        }
        lines.add(List.of("Файл(-ы)", filenameString.toString()));
        String date = EMPTY;
        if (!from.equals(LocalDate.MIN)) {
            date = from.toString();
        }
        lines.add(List.of("Начальная дата", date));
        date = EMPTY;
        if (!to.equals(LocalDate.MAX)) {
            date = to.toString();
        }
        lines.add(List.of("Конечная дата", date));
        lines.add(List.of("Количество запросов", String.valueOf(logs.size())));

        long sumOfBytes = logs.stream().mapToLong(Log::bytesSend).sum();

        lines.add(List.of("Средний размер ответа", sumOfBytes / logs.size() + "b"));
        return Statistic.createTable(lines, columnNames, title, format);
    }

    public Path analyse() {
        //логи с from до to
        logs = logs.stream()
            .filter(log ->
                log.offsetDateTime().isAfter(OffsetDateTime.of(from, LocalTime.MIN, ZoneOffset.UTC))
                    &&
                    log.offsetDateTime().isBefore(OffsetDateTime.of(to, LocalTime.MAX, ZoneOffset.UTC)))
            .toList();
        List<Statistic> statistics = List.of(
            new TopRequestStatistic(),
            new TopRequestCountStatistic(),
            new TopRequestTypeStatistic(),
            new TopResponseCodeStatistic()
        );
        //Все строчки для статистики
        List<String> lines = createGeneralStatistic();
        Format finalFormat = format;
        statistics.forEach(x -> lines.addAll(x.compute(logs, finalFormat)));
        Path path = createFile(lines);
        LOGGER.info("Statistic created: " + path.toString());
        return path;
    }

    public Path createFile(List<String> lines) {
        Path file;

        if (format.equals(Format.MARKDOWN)) {
            file = Path.of(ROOT.toString(), "statistic.md");
        } else {
            file = Path.of(ROOT.toString(), "statistic.adoc");
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

    public static Map<String, List<String>> getFiles(String path) throws IOException, InterruptedException {
        //Название файла - строки файла
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
            List<Path> paths = findMe(ROOT, "glob:**/" + path);
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
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(matcher.group(DATETIME), f);
                String requestType = matcher.group(REQUEST_TYPE);
                String request = matcher.group(REQUEST);
                int status = Integer.parseInt(matcher.group(STATUS));
                int bytesSend = Integer.parseInt(matcher.group(BYTES));
                String httpReferer = matcher.group(REFERER);
                String httpUserAgent = matcher.group(USER_AGENT);
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

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Format getFormat() {
        return format;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public List<String> getFilenames() {
        return filenames;
    }
}
