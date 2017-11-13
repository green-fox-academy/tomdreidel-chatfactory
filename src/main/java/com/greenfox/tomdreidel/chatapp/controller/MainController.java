package com.greenfox.tomdreidel.chatapp.controller;

import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import com.greenfox.tomdreidel.chatapp.service.LogService;
import com.greenfox.tomdreidel.chatapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  LogService logService;

  @Autowired
  UserService userService;

  @ModelAttribute
  protected void logging(HttpServletRequest request, HttpServletResponse response) {
    logService.addLog(request, response);

//    @ExceptionHandler


//    Cant get ERROR status
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
  public String addUser(@ModelAttribute ChatUser user) {
    userService.addUser(user);
    return "redirect:/users";
  }
}
