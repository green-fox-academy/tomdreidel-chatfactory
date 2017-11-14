package com.greenfox.tomdreidel.chatapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;
  private Timestamp dateCreated;

  private String userName;
  private String userAddress;

  public ChatUser(String userName) {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
    this.userName = userName;
  }

  public ChatUser() {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
  }

  public long getUserId() {
    return userId;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String sendToAPI() {
    return "{\"title\" : \"" +
        userName + "\"" +
        "}";
  }
}
