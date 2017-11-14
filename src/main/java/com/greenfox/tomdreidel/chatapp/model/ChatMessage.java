package com.greenfox.tomdreidel.chatapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChatMessage {
  @Id
  private long id;
  private String userName;
  private String text;
  private Timestamp sendDate;

  public ChatMessage(String userName, String text) {
    Random newId = new Random();
    this.id = newId.nextInt(8999999) + 1000000;
    this.userName = userName;
    this.text = text;
    this.sendDate = Timestamp.valueOf(LocalDateTime.now());
  }

  public ChatMessage() {
    Random newId = new Random();
    this.id = newId.nextInt(8999999) + 1000000;
    this.sendDate = Timestamp.valueOf(LocalDateTime.now());
  }

  public long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getSendDate() {
    return sendDate;
  }

}
