package com.example.caching.app.mapper;

import com.example.caching.app.dto.UserResp;
import com.example.caching.app.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserResp> mapToDtoList(Iterable<User> users);
}
