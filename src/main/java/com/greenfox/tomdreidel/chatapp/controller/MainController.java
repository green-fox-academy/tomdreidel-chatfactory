package com.greenfox.tomdreidel.chatapp.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import com.greenfox.tomdreidel.chatapp.service.LogService;
import com.greenfox.tomdreidel.chatapp.service.MessageService;
import com.greenfox.tomdreidel.chatapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  LogService logService;

  @Autowired
  UserService userService;

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

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/logs")
  public String logs(Model model) {
    model.addAttribute("logList", logService.listAllLogs());
    return "logs";
  }

  @RequestMapping("/messages")
  public String messages(Model model) {
    model.addAttribute("message", new ChatMessage());
    model.addAttribute("messages", messageService.listAllMessages());
    return "messages";
  }

  @RequestMapping("/users")
  public String userList(Model model) {
    model.addAttribute("userList", userService.listAllUsers());
    return "users";
  }

  @GetMapping("/users/add")
  public String addUser(Model model) {
    model.addAttribute("user", new ChatUser());
    return "adduser";
  }

  @PostMapping("/users/add")
  public String addUser (@ModelAttribute ChatUser user) throws Exception {
    if (user.getUserName().equals("p")) {
      throw new Exception();
    }
    userService.addUser(user);
    return "redirect:/users";
  }


  @RequestMapping(value = "/messages/send")
  public String sendMessage(@RequestBody ChatMessage message) throws JsonParseException {


    messageService.addMessage(message);
    messageService.sendMessage(message);
    return "redirect:/messages";
  }
}
