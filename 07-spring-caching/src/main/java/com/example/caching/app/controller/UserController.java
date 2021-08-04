package com.example.caching.app.controller;

import com.example.caching.app.dto.UserResp;
import com.example.caching.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResp> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(params="name")
    public List<UserResp> getUsersByNameContains(@RequestParam(value = "name") String name){
        return userService.getUsersByNameContains(name);
    }

    @GetMapping(params="city")
    public List<UserResp> getUsersByCity(@RequestParam(value = "city") String city){
        return userService.getAllUsersByCity(city);
    }

    @GetMapping(params="zipCode")
    public List<UserResp> getUsersByZipCode(@RequestParam(value = "zipCode") Integer zipCode){
        return userService.getAllUsersByZipCode(zipCode);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

}
