package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.AssertionConcern;
import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;

public class User extends AssertionConcern implements Record {
    @Inject
    OrderMapper orderMapper;

    private long id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    private User() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("name", getName());
            put("id", getId());
            put("uri", routes.userUrl(User.this));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public Order placeOrder(Map<String, Object> orderInfo) {
        orderInfo.put("user_id", getId());
        orderMapper.save(orderInfo);
        return orderMapper.findById(Long.valueOf(orderInfo.get("id").toString()));
    }

    public Optional<Order> findOrderById(Long id) {
        return Optional.ofNullable(orderMapper.findById(id));
    }
}
