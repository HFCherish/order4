package com.thoughtworks.ketsu.domain;

public class OrderItem {
    private long productId;
    private int quantity;
    private double amount;

    private OrderItem() {
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }
}
