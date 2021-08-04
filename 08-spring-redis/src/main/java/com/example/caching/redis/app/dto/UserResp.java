package com.example.caching.redis.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash("User")
public class UserResp implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
    private AddressResp address;
}