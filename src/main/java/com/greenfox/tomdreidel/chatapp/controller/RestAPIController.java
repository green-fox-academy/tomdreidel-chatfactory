package com.greenfox.tomdreidel.chatapp.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.Status;
import com.greenfox.tomdreidel.chatapp.model.Wrapper;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import com.greenfox.tomdreidel.chatapp.service.LogService;
import com.greenfox.tomdreidel.chatapp.service.MessageService;
import com.greenfox.tomdreidel.chatapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestAPIController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  LogService logService;

  @Autowired
  MessageService messageService;

  @ExceptionHandler(Exception.class)
  public String handleError(HttpServletRequest request) {
    logService.addLog(request, "EXCEPTION");
    return "redirect:/users";
  }

  @ModelAttribute
  protected void logging(HttpServletRequest request) {
    logService.addLog(request, "REQUEST");
  }


//  @RequestMapping(value = "/users/{id}/send")
//  public ResponseEntity<String> sendUser(@PathVariable long id) {
//
//    RestTemplate sendUser = new RestTemplate();
//
//    HttpHeaders header = new HttpHeaders();
////    header.set("Host", "localhost:8080");
//    header.set("Content-Type", "application/json");
//    header.set("Cache-Control", "no-cache");
//
//    MultiValueMap<String, String> content = new LinkedMultiValueMap();
//    content.add("title", userRepository.findOne(id).getUserName().toUpperCase());
//
//    HttpEntity<MultiValueMap<String, String>> builtRequest = new HttpEntity<>(content, header);
//
//    ResponseEntity<String> message = sendUser
//        .exchange("https://tocan.herokuapp.com/add", HttpMethod.POST, builtRequest, String.class);
//


//    final String uri = "http://localhost:8080/users/add/rest";

//    ChatUser newUser = new ChatUser(userRepository.findOne(id).getUserName().toUpperCase());

//    addRestUser(newUser);
//    addRestTodo(new Todo(userRepository.findOne(id).getUserName()));
//    return message;

//    ChatUser result = restTemplate.postForObject( uri, newUser, ChatUser.class);
//    return result;

//  }

  @RequestMapping(value = "/users/todo/{id}")
  public String sendAPI(@PathVariable long id) throws JsonParseException {

    userService.sendAPI(id);

    return "redirect:/users";
  }



  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public ResponseEntity receiveMessage(@RequestBody Wrapper wrapper) {
//    if (wrapper.getMessage().getSendDate()==null||wrapper.getMessage().getText()==null||wrapper.getMessage().getUserName()==null) {
//      return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
//    } else {
      messageService.addMessage(wrapper.getMessage());
      return new ResponseEntity(new Status("ok"), HttpStatus.OK);
//    }
  }

}
