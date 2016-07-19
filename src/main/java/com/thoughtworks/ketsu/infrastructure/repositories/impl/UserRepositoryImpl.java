package com.thoughtworks.ketsu.infrastructure.repositories.impl;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    @Inject
    UserMapper userMapper;

    @Override
    public User save(Map<String, Object> userInfo) {
        userMapper.save(userInfo);
        return userMapper.findById(Long.valueOf(userInfo.get("id").toString()));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }
}
