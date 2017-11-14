package com.greenfox.tomdreidel.chatapp.service;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.Client;
import com.greenfox.tomdreidel.chatapp.model.Status;
import com.greenfox.tomdreidel.chatapp.model.Wrapper;
import com.greenfox.tomdreidel.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  public void addMessage(ChatMessage message) {

    messageRepository.save(message);
  }

  public void sendMessage(ChatMessage message) {
    String url = System.getenv("https://oraclechat.herokuapp.com/api/message/receive");
//    String url = System.getenv("CHAT_APP_PEER_ADDRESS");
//    String url = "https://chatfactory.herokuapp.com/api/message/receive";
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    Client client = new Client();
    ChatMessage chatMessage = new ChatMessage();
    chatMessage.setText("bezik");
    Wrapper sendIt = new Wrapper(client, chatMessage);
    HttpEntity<Wrapper> httpEntity = new HttpEntity<Wrapper>(sendIt, headers);
    RestTemplate template = new RestTemplate();
    template.postForObject(url, httpEntity, ResponseEntity.class);
  }

  public Iterable<ChatMessage> listAllMessages() {
    return messageRepository.findAll();
  }

}
