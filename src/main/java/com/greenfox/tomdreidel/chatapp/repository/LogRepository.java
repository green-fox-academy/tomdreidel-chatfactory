package com.greenfox.tomdreidel.chatapp.repository;

import com.greenfox.tomdreidel.chatapp.model.LogItem;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<LogItem, Long> {

}
