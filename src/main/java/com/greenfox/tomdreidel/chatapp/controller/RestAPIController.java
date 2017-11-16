package com.greenfox.tomdreidel.chatapp.controller;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.Client;
import com.greenfox.tomdreidel.chatapp.model.MessageContainer;
import com.greenfox.tomdreidel.chatapp.model.Status;
import com.greenfox.tomdreidel.chatapp.model.Wrapper;
import com.greenfox.tomdreidel.chatapp.repository.UserRepository;
import com.greenfox.tomdreidel.chatapp.service.LogService;
import com.greenfox.tomdreidel.chatapp.service.MessageService;
import com.greenfox.tomdreidel.chatapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @Autowired
  SimpMessagingTemplate template;

  @ExceptionHandler(Exception.class)
  public String handleError(HttpServletRequest request) {
    logService.addLog(request, "EXCEPTION");
    return "redirect:/";
  }

  @ModelAttribute
  protected void logging(HttpServletRequest request) {
    logService.addLog(request, "REQUEST");
  }

  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public ResponseEntity receiveMessage(@RequestBody Wrapper wrapper) {
    if (wrapper.getMessage().getTimestamp()==null||wrapper.getMessage().getText()==null||wrapper.getMessage().getUsername()==null) {
      return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
    } else {
      messageService.addMessage(wrapper, 1);
      template.convertAndSend("/socket", "hello");
      return new ResponseEntity(new Status("ok"), HttpStatus.OK);
    }
  }

  @GetMapping("/api/message/all")
  @CrossOrigin("*")
  public ResponseEntity getMessageList() {
    return new ResponseEntity<>(messageService.listAllMessages(), HttpStatus.OK);
  }

  @GetMapping("/api/message/current")
  @CrossOrigin("*")
  public ResponseEntity getCurrentList() {
    return new ResponseEntity<>(messageService.paginatedMessages(), HttpStatus.OK);
  }

  @GetMapping("/api/user/all")
  @CrossOrigin("*")
  public ResponseEntity getUserList() {
    return new ResponseEntity<>(userService.listAllUsers(), HttpStatus.OK);
  }

  @PostMapping("api/message/send")
  @CrossOrigin("*")
  public ResponseEntity sendAPI(@RequestBody ChatMessage message, @RequestParam(name = "recipient", required = false, defaultValue = "157") long id) {
    Wrapper wrapper = new Wrapper(new Client(), message);

    if (wrapper.getMessage().getTimestamp()==null||wrapper.getMessage().getText()==null||wrapper.getMessage().getUsername()==null) {
      return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
    } else {
      messageService.addMessage(wrapper, id);
      messageService.sendMessage(wrapper, id);
      template.convertAndSend("/socket", "hello");
      return new ResponseEntity(new Status("ok"), HttpStatus.OK);
    }

  }





}
