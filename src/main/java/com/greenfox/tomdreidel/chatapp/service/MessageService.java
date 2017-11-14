package com.greenfox.tomdreidel.chatapp.service;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.Client;
import com.greenfox.tomdreidel.chatapp.model.Status;
import com.greenfox.tomdreidel.chatapp.model.Wrapper;
import com.greenfox.tomdreidel.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  public void addMessage(ChatMessage message) {

    messageRepository.save(message);
  }

  public Status sendMessage(ChatMessage message) {
    RestTemplate template = new RestTemplate();
    String url = System.getenv("CHAT_APP_PEER_ADDRESS");
    Wrapper sendIt = new Wrapper(new Client(), message);
    HttpEntity<Wrapper> httpEntity = new HttpEntity<>(sendIt);
    Status response = template.postForObject(url, httpEntity, Status.class);
    return response;
  }

  public Iterable<ChatMessage> listAllMessages() {
    return messageRepository.findAll();
  }

  public Iterable<ChatMessage> paginatedMessages() {
    return messageRepository.messagePaginated();
  }
}
