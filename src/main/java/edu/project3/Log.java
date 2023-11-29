package edu.project3;

import java.time.OffsetDateTime;

public record Log(String remoteAddress,
                  String remoteUser,
                  OffsetDateTime offsetDateTime,
                  String request,
                  String requestType,
                  int status,
                  int bytesSend,
                  String httpReferer,
                  String httpUserAgent) {

}
