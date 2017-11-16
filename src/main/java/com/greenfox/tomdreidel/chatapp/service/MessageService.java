package com.greenfox.tomdreidel.chatapp.service;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.Client;
import com.greenfox.tomdreidel.chatapp.model.MessageContainer;
import com.greenfox.tomdreidel.chatapp.model.Status;
import com.greenfox.tomdreidel.chatapp.model.Wrapper;
import com.greenfox.tomdreidel.chatapp.repository.MessageRepository;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  UserRepository userRepository;

  public void addMessage(Wrapper wrapper, long id) {

    messageRepository.save(wrapper.getMessage());
  }

  public Status sendMessage(Wrapper wrapper, long id) {
    RestTemplate restTemplate = new RestTemplate();

//    String url = System.getenv("CHAT_APP_PEER_ADDRESS");

    String url = userRepository.handleById(id).get(0).getUserAddress();
    System.out.println(url);

    Wrapper sendIt = new Wrapper(new Client(), wrapper.getMessage());

    HttpEntity<Wrapper> httpEntity = new HttpEntity<>(sendIt);
    Status response = restTemplate.postForObject(url, httpEntity, Status.class);
    return response;
  }

  public Iterable<ChatMessage> listAllMessages() {
    return messageRepository.findAll();
  }

  public Iterable<ChatMessage> paginatedMessages() {
    return messageRepository.messagePaginated();
  }

  public MessageContainer getMessageList() {
    MessageContainer response = new MessageContainer();
    listAllMessages().forEach(ChatMessage -> response.addMessage(ChatMessage));
    return response;
  }
}
