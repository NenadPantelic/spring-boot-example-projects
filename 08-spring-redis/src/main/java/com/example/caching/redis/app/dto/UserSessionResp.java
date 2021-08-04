package com.example.caching.redis.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash("Session")
public class UserSessionResp implements Serializable {
    private String id;
    private String username;
    private Instant loginTime;
    private String browser;
}
