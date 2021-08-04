package com.example.caching.redis.app.service;

import com.example.caching.redis.app.dto.UserSessionResp;

public interface UserSessionService {
    void createSession();
    UserSessionResp getSession();
}
