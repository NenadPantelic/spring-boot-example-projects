package com.example.caching.redis.app.model.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user:session")
public class UserSession {
    private String id;
    private String username;
    private Instant loginTime;
    private String browser;
}
