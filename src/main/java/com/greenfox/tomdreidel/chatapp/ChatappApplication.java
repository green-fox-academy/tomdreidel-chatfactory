package com.greenfox.tomdreidel.chatapp;

import com.greenfox.tomdreidel.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatappApplication {

  @Autowired
  UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

}
