package com.programming.techie.springredditclone;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class BaseTest {

    @Container
    static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withDatabaseName("spring-reddit-test-db")
            .withUsername("testuser")
            .withPassword("pass");

    static{
        mySQLContainer.start();
    }
}
