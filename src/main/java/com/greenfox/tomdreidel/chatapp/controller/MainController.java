package com.greenfox.tomdreidel.chatapp.controller;

import com.greenfox.tomdreidel.chatapp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  LogRepository logRepository;

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("logList", logRepository.findAll());
    return "index";
  }

}
