package com.example.caching.app.service;

import com.example.caching.app.dto.UserResp;
import com.example.caching.app.model.User;

import java.util.List;

public interface UserService {

    List<UserResp> getAllUsers();
    List<UserResp> getUsersByNameContains(String name);
    List<UserResp> getAllUsersByCity(String city);
    List<UserResp> getAllUsersByZipCode(Integer zipCode);
    User deleteUser(Long id);

}
