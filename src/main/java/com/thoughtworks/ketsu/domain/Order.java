package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import java.util.ArrayList;
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
    private DateTime createdAt;

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {

        List items = new ArrayList<>();
        for( OrderItem item: orderItems) {
            items.add(item.toJson(routes));
        }
        return new HashMap<String, Object>() {{
            put("uri", routes.orderUrl(Order.this));
            put("name", getName());
            put("address", getAddress());
            put("phone", getPhone());
            put("total_price", getTotalPrice());
            put("created_at", getCreatedAt());
            put("order_items", items);
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

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
