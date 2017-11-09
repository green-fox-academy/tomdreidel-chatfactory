package com.greenfox.tomdreidel.chatapp;

import com.greenfox.tomdreidel.chatapp.model.LogItem;
import com.greenfox.tomdreidel.chatapp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatappApplication implements CommandLineRunner {

	@Autowired
  LogRepository logRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logRepository.save(new LogItem());
		logRepository.save(new LogItem());
		logRepository.save(new LogItem());
	}
}
