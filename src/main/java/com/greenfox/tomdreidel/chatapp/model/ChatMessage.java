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
  private String username;
  private String text;
  private Timestamp timestamp;

  public ChatMessage(String text) {
    Random newId = new Random();
    this.id = newId.nextInt(8999999) + 1000000;
    this.username = "tomdreidel";
    this.text = text;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now());
  }

  public ChatMessage() {
    Random newId = new Random();
    this.username = "tomdreidel";
    this.id = newId.nextInt(8999999) + 1000000;
    this.timestamp = Timestamp.valueOf(LocalDateTime.now());
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

}
