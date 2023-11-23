package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class AnalyserTest {
    Path root = Path.of(Paths.get("")
        .toAbsolutePath()
        .toString(), "src/test/java/edu/project3");

    @Test
    void convertToLog() {
        Log log =
            new Log("93.180.71.3",
                "-",
                OffsetDateTime.of(LocalDateTime.parse("2015-05-17T08:05:32"), ZoneOffset.UTC),
                "/downloads/product_1 HTTP/1.1",
                "GET", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            );
        List<Log> logs = Analyser.convertToLog(List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));
        Assertions.assertEquals(1, logs.size());
        Assertions.assertEquals(log, logs.get(0));
        String dir = System.getProperty("user.home");
        System.out.println(dir);
    }

    @Test
    void analyseFromHTTP() {
        String[] args = {"--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--format", "adoc"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        Assertions.assertEquals(LocalDate.MIN, analyser.getFrom());
        Assertions.assertEquals(LocalDate.MAX, analyser.getTo());
        Assertions.assertEquals(51462, analyser.getLogs().size());
        Assertions.assertEquals(1, analyser.getFilenames().size());
        Assertions.assertEquals(Format.ADOC, analyser.getFormat());
    }

    @Test
    void analyseFromPath() {
        String[] args = {"--path", "logs/**/*.txt", "--format", "adoc"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        Assertions.assertEquals(LocalDate.MIN, analyser.getFrom());
        Assertions.assertEquals(LocalDate.MAX, analyser.getTo());
        Assertions.assertEquals(102, analyser.getLogs().size());
        Assertions.assertEquals(1, analyser.getFilenames().size());
        Assertions.assertEquals(Format.ADOC, analyser.getFormat());
    }

    @Test
    void analyseWithFromDate() {
        String[] args = {"--path", "logs/**/*.txt", "--from", "2015-01-31", "--format", "adoc"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        analyser.analyse(root);
        Assertions.assertEquals(LocalDate.of(2015, Month.JANUARY, 31), analyser.getFrom());
        Assertions.assertEquals(LocalDate.MAX, analyser.getTo());
        Assertions.assertEquals(99, analyser.getLogs().size());
        Assertions.assertEquals(1, analyser.getFilenames().size());
        Assertions.assertEquals(Format.ADOC, analyser.getFormat());
    }

    @Test
    void analyseWithToDate() {
        String[] args = {"--path", "logs/**/*.txt", "--to", "2015-01-31", "--format", "adoc"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        analyser.analyse(root);
        Assertions.assertEquals(LocalDate.of(2015, Month.JANUARY, 31), analyser.getTo());
        Assertions.assertEquals(LocalDate.MIN, analyser.getFrom());
        Assertions.assertEquals(3, analyser.getLogs().size());
        Assertions.assertEquals(1, analyser.getFilenames().size());
        Assertions.assertEquals(Format.ADOC, analyser.getFormat());
    }

    @Test
    void analyseWithMarkdown() {
        String[] args = {"--path", "logs/**/*.txt", "--to", "2015-01-31", "--format", "markdown"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        Path path = analyser.analyse(root);
        Assertions.assertTrue(path.toString().endsWith(".md"));
    }

    @Test
    void analyseWithADOC() {
        String[] args = {"--path", "logs/**/*.txt", "--to", "2015-01-31", "--format", "adoc"};
        Analyser analyser = Analyser.getValuesFromConsole(args, root);
        Path path = analyser.analyse(root);
        Assertions.assertTrue(path.toString().endsWith(".adoc"));
    }

}
