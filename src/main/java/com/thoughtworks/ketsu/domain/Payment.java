package com.thoughtworks.ketsu.domain;

import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class Payment implements Record{
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

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("pay_type", getType());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }
}
