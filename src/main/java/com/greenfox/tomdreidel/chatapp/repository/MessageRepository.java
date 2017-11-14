package com.greenfox.tomdreidel.chatapp.repository;

import com.greenfox.tomdreidel.chatapp.model.ChatMessage;
import com.greenfox.tomdreidel.chatapp.model.LogEntry;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<ChatMessage, Long> {

//  @Query(value = "SELECT * FROM log_entry ORDER BY date_created DESC", nativeQuery = true)
//  public List<LogEntry> recentLogFirst();

}
