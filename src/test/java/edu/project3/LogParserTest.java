package edu.project3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LogParserTest {

    @Test
    void convertToLog() {
        Log log =
            new Log("93.180.71.3",
                "-",
                OffsetDateTime.of(LocalDateTime.parse("2015-05-17T08:05:32"), ZoneOffset.UTC),
                "/downloads/product_1 HTTP/1.1",
                "GET", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            );
        Log parsedLog = LogParser.convertToLog(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"");
        Assertions.assertEquals(log, parsedLog);
    }

    @Test
    void parse() {
        Log log =
            new Log("93.180.71.3",
                "-",
                OffsetDateTime.of(LocalDateTime.parse("2015-05-17T08:05:32"), ZoneOffset.UTC),
                "/downloads/product_1 HTTP/1.1",
                "GET", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            );
        Log log2 = new Log("93.180.71.4",
            "-",
            OffsetDateTime.of(LocalDateTime.parse("2015-05-17T08:05:32"), ZoneOffset.UTC),
            "/downloads/product_1 HTTP/1.1",
            "GET", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        );
        List<Log> parsedLogs = LogParser.parse(List.of(
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
            ,
            "93.180.71.4 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""));
        Assertions.assertEquals(log, parsedLogs.get(0));
        Assertions.assertEquals(log2, parsedLogs.get(1));
    }
}
