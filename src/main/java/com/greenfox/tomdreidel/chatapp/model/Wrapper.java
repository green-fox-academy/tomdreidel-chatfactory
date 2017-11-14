package com.greenfox.tomdreidel.chatapp.model;

public class Wrapper {

  private Client client;
  private ChatMessage message;

  public Wrapper(Client client, ChatMessage message) {
    this.client = client;
    this.message = message;
  }

  public Wrapper() {
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public ChatMessage getMessage() {
    return message;
  }

  public void setMessage(ChatMessage message) {
    this.message = message;
  }
}
