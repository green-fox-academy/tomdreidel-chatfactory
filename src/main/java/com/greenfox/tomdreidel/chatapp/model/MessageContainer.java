package com.greenfox.tomdreidel.chatapp.model;

import java.util.ArrayList;

public class MessageContainer extends ArrayList<ChatMessage> {

  public MessageContainer() {
  }

  public void addMessage(ChatMessage message) {
    this.add(message);
  }


}
