package com.thoughtworks.ketsu.domain;

import org.joda.time.DateTime;

public class Payment{
    private long orderId;
    private double amount;
    private PayType type;
    private DateTime createdAt;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public PayType getType() {
        return type;
    }


}
