package com.greenfox.tomdreidel.chatapp.model;

import org.springframework.http.HttpEntity;

public class Todo extends HttpEntity<String> {

  private String title;

  public Todo(String title) {
    this.title = title;
  }

  public String sendToAPI() {
    return "{\"title\" : \"" +
        title + "\"" +
        "}";
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
