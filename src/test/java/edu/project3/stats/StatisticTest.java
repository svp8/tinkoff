package edu.project3.stats;

import edu.project3.Analyser;
import edu.project3.Format;
import edu.project3.Log;
import edu.project3.LogParser;
import edu.project3.table.TableCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {
    static List<Log> logs;
    TableCreator tableCreator = TableCreator.getCreator(Format.ADOC);

    @BeforeAll static void init() {
        List<String> lines = List.of(
            "65.39.197.164 - - [29/May/2015:09:05:00 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 336 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "65.39.197.164 - - [29/May/2015:09:05:21 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 333 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "65.39.197.164 - - [29/May/2015:09:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 334 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "65.39.197.164 - - [29/May/2015:09:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 331 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "65.39.197.164 - - [29/May/2015:09:05:12 +0000] \"POST /downloads/product_1 HTTP/1.1\" 404 331 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\"",
            "6.249.65.159 - - [06/Nov/2014:19:10:38 +0600] \"POST /news/53f8d72920ba2744fe873ebc.html HTTP/1.1\" 404 177 \"-\" \"Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"",
            "66.249.65.3 - - [06/Nov/2014:19:11:24 +0600] \"DELETE /?q=%E0%A6%AB%E0%A6%BE%E0%A7%9F%E0%A6%BE%E0%A6%B0 HTTP/1.1\" 200 4223 \"-\" \"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"",
            "66.249.65.62 - - [06/Nov/2014:19:12:14 +0600] \"GET /?q=%E0%A6%A6%E0%A7%8B%E0%A7%9F%E0%A6%BE HTTP/1.1\" 200 4356 \"-\" \"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\""
        );

        logs = LogParser.parse(lines);
    }

    public static Stream<Arguments> compute() {
        return Stream.of(
            Arguments.of(new TopRequestCountStatistic(), "|65.39.197.164 |5"),
            Arguments.of(new TopRequestStatistic(), "/downloads/product_1 |5"),
            Arguments.of(new TopRequestTypeStatistic(), "|GET |5"),
            Arguments.of(new TopResponseCodeStatistic(), "|404 |Not Found |6 ")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void compute(Statistic statistic, String expected) {
        List<String> lines = statistic.compute(logs, tableCreator);
        lines.forEach(System.out::println);
        Assertions.assertTrue(lines.get(5).contains(expected));
    }

}
