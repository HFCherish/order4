package com.thoughtworks.ketsu.domain;

public class Order {
    private long id;
    private long userId;
    private String name;
    private String address;
    private String phone;

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
}
