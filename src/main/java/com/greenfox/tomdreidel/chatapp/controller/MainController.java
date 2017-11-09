package com.greenfox.tomdreidel.chatapp.controller;

import com.greenfox.tomdreidel.chatapp.model.LogItem;
import com.greenfox.tomdreidel.chatapp.repository.LogRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  LogRepository logRepository;

  @RequestMapping("/")
  public String index(HttpServletRequest requestToCache, Model model) {
    logRepository.save(new LogItem("LOG", requestToCache.getServletPath(), requestToCache.getMethod(), requestToCache.getRemoteAddr()));
    model.addAttribute("logList", logRepository.findAll());
    return "index";
  }

}
