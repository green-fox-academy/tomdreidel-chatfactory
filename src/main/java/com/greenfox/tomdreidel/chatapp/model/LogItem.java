package com.greenfox.tomdreidel.chatapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long logId;
  private Timestamp dateCreated;

  private String logLevel;
  private String requestPath;
  private String requestMethod;
  private String requestData;

  public LogItem() {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
  }

  public LogItem(String logLevel, String requestPath, String requestMethod, String requestData) {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
    this.logLevel = logLevel;
    this.requestPath = requestPath;
    this.requestMethod = requestMethod;
    this.requestData = requestData;
  }

  public long getLogId() {
    return logId;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public String getRequestPath() {
    return requestPath;
  }

  public void setRequestPath(String requestPath) {
    this.requestPath = requestPath;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(String logLevel) {
    this.logLevel = logLevel;
  }

  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
  }
}
