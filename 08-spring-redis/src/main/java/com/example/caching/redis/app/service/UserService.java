package com.example.caching.redis.app.service;

import com.example.caching.redis.app.dto.UserResp;
import com.example.caching.redis.app.model.User;

import java.util.List;

public interface UserService {

    List<UserResp> getAllUsers();
    List<UserResp> getUsersByNameContains(String name);
    List<UserResp> getAllUsersByCity(String city);
    List<UserResp> getAllUsersByZipCode(Integer zipCode);
    User deleteUser(Long id);

}