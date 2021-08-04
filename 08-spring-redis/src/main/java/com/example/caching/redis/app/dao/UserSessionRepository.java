package com.example.caching.redis.app.dao;

import com.example.caching.redis.app.model.cache.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
