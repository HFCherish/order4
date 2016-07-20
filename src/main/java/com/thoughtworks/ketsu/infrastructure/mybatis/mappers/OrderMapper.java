package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    Order findById(@Param("id") Long id);

    void save(@Param("info") Map<String, Object> orderInfo);

    List<Order> findAll(@Param("userId") long userId);
}
