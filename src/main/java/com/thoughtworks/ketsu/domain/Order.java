package com.thoughtworks.ketsu.domain;

public class Order {
    private long id;
    private long userId;

    public Order(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }
}
