package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.BaseTest;
import com.programming.techie.springredditclone.model.Post;
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
public class PostRepositoryTest extends BaseTest {

    // koriscenje @Container i @Testcontainers anotacija kreira svaki put docker container konekciju
    // sto je jako sporo -> umesto toga, koristi se SingletonPattern da samo jednu konekciju imamo
//    @Container
//    MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
//            .withDatabaseName("spring-reddit-test-db")
//            .withUsername("testuser")
//            .withPassword("pass");

    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldSavePost(){
        Post expectedPost = new Post(null, "First post", "http://url.site", "Test",
                0, null, Instant.now(), null);
        Post actualPost = postRepository.save(expectedPost);
        assertThat(actualPost)
                .usingRecursiveComparison()
                .ignoringFields("postId")
                .isEqualTo(expectedPost);
    }

}
