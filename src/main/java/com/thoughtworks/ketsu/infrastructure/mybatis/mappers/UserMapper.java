package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    User findById(@Param("id") long id);

    void save(@Param("info") Map<String, Object> userInfo);


}
