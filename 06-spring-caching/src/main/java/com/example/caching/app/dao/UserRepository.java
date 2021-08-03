package com.example.caching.app.dao;

import com.example.caching.app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

    List<User> findByFirstNameContainsOrLastNameContains(String name1, String name2);
    List<User> findByAddressCity(String city);
    List<User> findByAddressZipCode(Integer zipCode);


}
