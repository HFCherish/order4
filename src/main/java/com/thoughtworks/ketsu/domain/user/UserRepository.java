package com.thoughtworks.ketsu.domain.user;

import java.util.Map;
import java.util.Optional;

public interface UserRepository {
    User save(Map<String, Object> userInfo);

    Optional<User> findById(Long id);
}
