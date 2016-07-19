package com.thoughtworks.ketsu.infrastructure.repositories.impl;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public User save(Map<String, Object> userInfo) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(new User("ljl"));
    }
}
