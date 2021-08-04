package com.example.caching.app.service.impl;

import com.example.caching.app.dao.UserRepository;
import com.example.caching.app.dto.UserResp;
import com.example.caching.app.mapper.UserMapper;
import com.example.caching.app.model.User;
import com.example.caching.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "users", sync = true)
    @Override
    public List<UserResp> getAllUsers() {
        log.info("Fetching users...");
        return userMapper.mapToDtoList(userRepository.findAll());
    }

    @Cacheable(cacheNames = "users-by-name", condition = "#name!='John'")
    @Override
    public List<UserResp> getUsersByNameContains(String name) {
        log.info("Fetching users by name: {}", name);
        return userMapper.mapToDtoList(userRepository.findByFirstNameContainsOrLastNameContains(name, name));
    }

    // condition = "#result!=null and #result.size()>0"
    @Cacheable(cacheNames = "users-by-city", key = "#city", unless = "#result==null or #result.size()==0")
    // condition and unless cannot stand with sync=true
    /* Key setting rules:
     * If no params are given, return 0.
     * If only one param is given, return that instance.
     * If more the one param is given, return a key computed from the hashes of all parameters.
     * */
    @Override
    public List<UserResp> getAllUsersByCity(String city) {
        log.info("Fetching users by city: {}", city);
        return userMapper.mapToDtoList(userRepository.findByAddressCity(city));
    }

    // cache only if zipCode is > 0 and result is non-empty list
    // unless=zipCode<=0
    @Cacheable(cacheNames = "users-by-zip-code", condition = "#zipCode > 0", unless = "#result==null or #result.size()==0")
    @Override
    public List<UserResp> getAllUsersByZipCode(Integer zipCode) {
        log.info("Fetching users by zip code: {}", zipCode);
        return userMapper.mapToDtoList(userRepository.findByAddressZipCode(zipCode));
    }

    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "users-by-city", key = "#result.getAddress().getCity()", condition = "#result!=null"),
            @CacheEvict(value = "users-by-name", allEntries = true),
            @CacheEvict(value = "users-by-zip-code", key = "#result.getAddress().getZipCode()", condition = "#result!=null"),
    })
    @Override
    public User deleteUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            User user = optUser.get();
            userRepository.delete(user);
            return user;
        }
        return null;
    }
}