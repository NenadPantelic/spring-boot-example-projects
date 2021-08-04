package com.example.caching.redis.app.mapper;

import com.example.caching.redis.app.dto.UserResp;
import com.example.caching.redis.app.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResp> mapToDtoList(Iterable<User> users);
}
