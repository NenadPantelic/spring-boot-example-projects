package com.example.pagingandsorting.demo;

import com.example.pagingandsorting.demo.utils.DbSeedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private DbSeedUtils seed;

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            seed.populateProducts(100000);
        };
    }
}
