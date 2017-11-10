package com.greenfox.tomdreidel.chatapp.controller;

import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import com.greenfox.tomdreidel.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Rest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @RequestMapping(value = "/users/{id}/send")
  public String sendUser(@PathVariable long id) {

//    RestTemplate sendUser = new RestTemplate();
//
//    HttpHeaders header = new HttpHeaders();
//    header.set("Host", "localhost:8080");
//    header.set("Content-Type", "application/json");
//    header.set("Cache-Control", "no-cache");
//
//    MultiValueMap<String, String> content = new LinkedMultiValueMap();
//    content.add("userName", userRepository.findOne(id).getUserName().toUpperCase());
//
//    HttpEntity<MultiValueMap<String, String>> builtRequest = new HttpEntity<>(content, header);
//
//    ResponseEntity<String> message = sendUser
//        .exchange("http://localhost:8080/users/add/rest", HttpMethod.POST, builtRequest, String.class);


//    final String uri = "http://localhost:8080/users/add/rest";

    ChatUser newUser = new ChatUser(userRepository.findOne(id).getUserName().toUpperCase());

    addRestUser(newUser);
    return "redirect:/users";

//    RestTemplate restTemplate = new RestTemplate();
//    ChatUser result = restTemplate.postForObject( uri, newUser, ChatUser.class);
//    return result;


  }

  @PostMapping("/users/add/rest")
  public String addRestUser(@RequestBody ChatUser user) {
    userService.addUser(user);
    return "redirect:/users";
  }

}
