package com.greenfox.tomdreidel.chatapp.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import com.greenfox.tomdreidel.chatapp.model.Todo;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public void addUser(ChatUser user) {
    userRepository.save(user);
  }

  public List<ChatUser> listAllUsers() {
    return userRepository.alphabeticalUserList();
  }

  public ResponseEntity<HttpEntity> sendAPI(@PathVariable long id) {

    String url = "https://tocan.herokuapp.com/add";
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    Todo sendIt = new Todo(userRepository.findOne(id).getUserName().toUpperCase());
    HttpEntity<Todo> httpEntity = new HttpEntity<Todo>(sendIt, headers);
    RestTemplate template = new RestTemplate();
    ResponseEntity<HttpEntity> response = template.postForObject(url, httpEntity, ResponseEntity.class);
    return response;
  }

}
