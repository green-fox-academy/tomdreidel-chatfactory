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
  private String githubHandle;
  private String imgLink;

  public ChatUser(String userName, String userAddress) {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
    this.userName = userName;
    this.userAddress = userAddress;
  }

  public ChatUser() {
    this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public String getGithubHandle() {
    return githubHandle;
  }

  public void setGithubHandle(String githubHandle) {
    this.githubHandle = githubHandle;
  }

  public String getImgLink() {
    return imgLink;
  }

  public void setImgLink(String imgLink) {
    this.imgLink = imgLink;
  }
}
