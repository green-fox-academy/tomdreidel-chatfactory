package com.greenfox.tomdreidel.chatapp.service;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestInterface {
  @POST("/users/add/rest")
  String createUser(@Body String user);

}
