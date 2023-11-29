package edu.project3;

import edu.project3.finders.DirectoryFinder;
import edu.project3.finders.Finder;
import edu.project3.finders.URLFinder;
import edu.project3.stats.Statistic;
import edu.project3.stats.TopRequestCountStatistic;
import edu.project3.stats.TopRequestStatistic;
import edu.project3.stats.TopRequestTypeStatistic;
import edu.project3.stats.TopResponseCodeStatistic;
import edu.project3.table.TableCreator;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UncommentedMain")
public class Analyser {
    public static final String EMPTY = "---";
    LocalDate from;
    LocalDate to;
    Format format;
    List<Log> logs;
    List<String> filenames;
    TableCreator tableCreator;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Path ROOT = Path.of(System.getProperty("user.dir"));

    public Analyser(LocalDate from, LocalDate to, Format format, List<Log> logs, List<String> filenames) {
        this.from = from;
        this.to = to;
        this.format = format;
        tableCreator = TableCreator.getCreator(format);
        this.logs = logs;
        this.filenames = filenames;
    }

    public static void main(String[] args) {
        Analyser analyser = getValuesFromConsole(args, ROOT);
        analyser.analyse(ROOT);
    }

    public static Analyser getValuesFromConsole(String[] args, Path root) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Wrong amount of arguments");
        }
        String path;
        LocalDate from = LocalDate.MIN;
        LocalDate to = LocalDate.MAX;
        Format format = Format.MARKDOWN;
        List<Log> logs = new ArrayList<>();
        List<String> filenames = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "--path":
                    path = args[i + 1];
                    Finder finder;
                    Map<String, List<String>> files;
                    if (path.startsWith("https://")) {
                        finder = new URLFinder();
                        files = finder.find(path, root);
                    } else {
                        finder = new DirectoryFinder();
                        files = finder.find(path, root);
                    }
                    filenames.addAll(files.keySet());
                    List<String> lines = new ArrayList<>();
                    files.values().forEach(lines::addAll);
                    logs = LogParser.parse(lines);
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
        List<String> table = tableCreator.createTable(lines, columnNames, title);
        return table;
    }

    public Path analyse(Path rootToSave) {
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
        //Все строчки для статистики, сначала добавляем общую информацию
        List<String> lines = new ArrayList<>(createGeneralStatistic());
        statistics.forEach(x -> lines.addAll(x.compute(logs, tableCreator)));
        Path path = FileUtil.createFileWithFormat(lines, format, rootToSave);
        LOGGER.info("Statistic created: " + path.toString());
        return path;
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
