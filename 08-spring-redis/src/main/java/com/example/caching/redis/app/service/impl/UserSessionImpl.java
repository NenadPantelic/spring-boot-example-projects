package com.example.caching.redis.app.service.impl;

import com.example.caching.redis.app.dao.UserSessionRepository;
import com.example.caching.redis.app.dto.UserSessionResp;
import com.example.caching.redis.app.model.cache.UserSession;
import com.example.caching.redis.app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserSessionImpl implements UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    private final String SESSION_ID = UUID.randomUUID().toString();


    @Override
    public void createSession() {
        userSessionRepository.save(new UserSession(SESSION_ID, "john_doe",
                Instant.now(), "Chrome"));
    }

    @Cacheable(cacheNames="user:session", sync = true)
    @Override
    public UserSessionResp getSession() {
        Optional<UserSession> optSession = userSessionRepository.findById(SESSION_ID);
        if (optSession.isEmpty()) {
            return null;
        }
        UserSession session = optSession.get();
        return new UserSessionResp(session.getId(), session.getUsername(), session.getLoginTime(),
                session.getBrowser());
    }
}
