package edu.project3.stats;

import edu.project3.Analyser;
import edu.project3.Format;
import edu.project3.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {
    static List<Log> logs;

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
        logs = Analyser.convertToLog(lines);
    }

    @Test
    void computeTopRequestCountStatistic() {
        Statistic statistic = new TopRequestCountStatistic();
        List<String> lines = statistic.compute(logs, Format.ADOC);
        lines.forEach(System.out::println);
        Assertions.assertTrue(lines.get(5).contains("|65.39.197.164 |5"));
    }
    @Test
    void computeTopRequestStatistic() {
        Statistic statistic = new TopRequestStatistic();
        List<String> lines = statistic.compute(logs, Format.ADOC);
        lines.forEach(System.out::println);
        Assertions.assertTrue(lines.get(5).contains("/downloads/product_1 |5"));
    }
    @Test
    void computeTopRequestTypeStatistic() {
        Statistic statistic = new TopRequestTypeStatistic();
        List<String> lines = statistic.compute(logs, Format.ADOC);
        lines.forEach(System.out::println);
        Assertions.assertTrue(lines.get(5).contains("|GET |5"));
    }
    @Test
    void computeTopResponseCodeStatistic() {
        Statistic statistic = new TopResponseCodeStatistic();
        List<String> lines = statistic.compute(logs, Format.ADOC);
        lines.forEach(System.out::println);
        Assertions.assertTrue(lines.get(5).contains("|404 |Not Found |6 "));
    }
}
