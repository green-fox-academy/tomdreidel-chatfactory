package com.greenfox.tomdreidel.chatapp.repository;

import java.util.List;
import com.greenfox.tomdreidel.chatapp.model.ChatUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ChatUser, Long> {

  @Query(value = "SELECT * FROM chat_user ORDER BY user_name ASC", nativeQuery = true)
  public List<ChatUser> alphabeticalUserList();

}
