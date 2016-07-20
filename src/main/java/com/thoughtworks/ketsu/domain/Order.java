package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Record {
    private long id;
    private long userId;
    private String name;
    private String address;
    private String phone;
    private List<OrderItem> orderItems;

    public Order(long userId) {
        this.userId = userId;
    }

    private Order() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(Order.this));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("total_price", getTotalPrice());

        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem item : orderItems) {
            totalPrice += item.getAmount() * item.getQuantity();
        }
        return totalPrice;
    }
}
