package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
// anotacija koja dohvata odgovorajuci application properties file -> u ovom slucaju application-test.properties
@ActiveProfiles("test")
// navodimo da ne treba sam Spring context ne zameni datasource, vec je on konfigurisan manuelno
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTestEmbedded {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser(){
        User user = new User(null, "test user", "secret password",
                "user@gmail.com", Instant.now(), false);
        User savedUser = userRepository.save(user);
        assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSaveUsersThroughSqlFile(){
        Optional<User> test = userRepository.findByUsername("testuser_sql");
        assertThat(test).isNotEmpty();
    }
}
