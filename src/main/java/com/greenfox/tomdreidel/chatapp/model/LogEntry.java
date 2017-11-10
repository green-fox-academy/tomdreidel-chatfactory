package com.greenfox.tomdreidel.chatapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long logId;
  private Timestamp dateCreated;

  private String logLevel;
  private String requestPath;
  private String requestMethod;
  private String requestAddress;
  private String requestData;

  public LogEntry() {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
  }

  public LogEntry(String logLevel, String requestPath, String requestMethod, String requestAddress, String requestData) {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
    this.logLevel = logLevel;
    this.requestPath = requestPath;
    this.requestMethod = requestMethod;
    this.requestAddress = requestAddress;
    this.requestData = requestData;
  }

  public long getLogId() {
    return logId;
  }

  public String getDateCreated() {
    return String.valueOf(dateCreated.toLocalDateTime());
  }

  public String getRequestPath() {
    return requestPath;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public String getRequestAddress() {
    return requestAddress;
  }

  public String getRequestData() {
    return requestData;
  }

  public String consoleLog() {
    return "New request: " +
        dateCreated +
        " " + logLevel +
        " " + requestPath +
        " " + requestMethod +
        " " + requestAddress +
        " " + requestData;
  }

}
