package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogParser {
    private LogParser() {
    }

    private static final Logger LOGGER = LogManager.getLogger();
    public static final int DATETIME = 3;
    public static final int REQUEST_TYPE = 5;
    public static final int REQUEST = 6;
    public static final int STATUS = 7;
    public static final int BYTES = 8;
    public static final int REFERER = 9;
    public static final int USER_AGENT = 10;

    public static List<Log> parse(List<String> lines) {
        List<Log> logs = new ArrayList<>();
        for (String line : lines) {
            Log log = convertToLog(line);
            if (log != null) {
                logs.add(log);
            } else {
                LOGGER.warn("Log was not parsed:" + line);
            }
        }
        return Collections.unmodifiableList(logs);
    }

    public static Log convertToLog(String line) {
        Pattern pattern = Pattern.compile("(.*) - (.*) \\[(.*)\\] \"((\\w*) (.*))\" (\\d*) (\\d*) \"(.*)\" \"(.*)\"");
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
            return log;
        } else {
            return null;
        }
    }
}
