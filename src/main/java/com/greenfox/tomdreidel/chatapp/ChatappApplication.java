package com.greenfox.tomdreidel.chatapp;

import com.greenfox.tomdreidel.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class ChatappApplication {

  @Autowired
  UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

}
