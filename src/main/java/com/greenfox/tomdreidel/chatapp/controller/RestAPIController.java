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
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    return "redirect:/";
  }

  @ModelAttribute
  protected void logging(HttpServletRequest request) {
    logService.addLog(request, "REQUEST");
  }


//  @SendTo("/messages")
//  public ChatMessage webSocketMessage(ChatMessage message) throws Exception {
//    Thread.sleep(1000); // simulated delay
//    return message;
//  }
  @MessageMapping
  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public ResponseEntity receiveMessage(@RequestBody Wrapper wrapper) {
    if (wrapper.getMessage().getCreatedAt()==null||wrapper.getMessage().getText()==null||wrapper.getMessage().getUserName()==null) {
      return new ResponseEntity(new Status("error", "Missing field(s)"), HttpStatus.UNAUTHORIZED);
    } else {
      messageService.addMessage(wrapper.getMessage());
      return new ResponseEntity(new Status("ok"), HttpStatus.OK);
    }
  }

}
