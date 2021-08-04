package com.example.caching.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private CacheManager cacheManager;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users", "users-by-city", "users-by-name", "users-by-zip-code");
    }
}
