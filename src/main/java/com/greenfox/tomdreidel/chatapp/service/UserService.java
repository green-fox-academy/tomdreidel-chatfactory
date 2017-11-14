package com.greenfox.tomdreidel.chatapp.service;

import java.util.List;
import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
}
