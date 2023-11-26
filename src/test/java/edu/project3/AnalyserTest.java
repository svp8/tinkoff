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
    Path root = Path.of("src/main/resources");

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
