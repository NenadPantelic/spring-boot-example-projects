package com.example.caching.app;

import com.example.caching.app.model.Address;
import com.example.caching.app.service.utils.DbSeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Autowired
    private DbSeed seed;

    @Bean
    CommandLineRunner runner() {
        return args -> {
            List<Address> addresses = seed.populateAddresses(50);
            seed.populateUsers(10000, addresses);
        };
    }

}
