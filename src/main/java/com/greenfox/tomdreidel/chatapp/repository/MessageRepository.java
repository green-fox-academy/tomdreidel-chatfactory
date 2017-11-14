package com.greenfox.tomdreidel.chatapp.repository;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<ChatMessage, Long> {

  @Query(value = "SELECT * FROM chat_message ORDER BY created_at ASC LIMIT 10 OFFSET ((SELECT count(*) from chat_message) - 10)", nativeQuery = true)
  public List<ChatMessage> messagePaginated();

}
