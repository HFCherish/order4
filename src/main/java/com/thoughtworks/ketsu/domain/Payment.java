package com.thoughtworks.ketsu.domain;

public class Payment {
    private long orderId;
    private double amount;

    public long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }
}
