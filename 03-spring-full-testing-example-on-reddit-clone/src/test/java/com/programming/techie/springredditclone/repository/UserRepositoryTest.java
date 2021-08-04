package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.BaseTest;
import com.programming.techie.springredditclone.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
//@Testcontainers
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends BaseTest {
    @Autowired
    private UserRepository userRepository;

//    @Container
//    MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
//            .withDatabaseName("spring-reddit-test-db")
//            .withUsername("testuser")
//            .withPassword("pass");

    @Test
    public void shouldSaveUser(){
        User user = new User(null, "test user", "secret password",
                "user@gmail.com", Instant.now(), false);
        User savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
    }

}
