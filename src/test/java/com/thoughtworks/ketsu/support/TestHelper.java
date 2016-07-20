package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static final String PRODUCT_NAME = "Imran";
    public static final String PRODUCT_DESC = "teacher";
    public static final String USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "JL.898-";

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public static User prepareUser(UserRepository userRepository) {
        return userRepository.save(userJsonForTest(USER_NAME));
    }

    public static Map<String, Object> paymentJsonForTest() {
        return new HashMap<String, Object>() {{
            put("pay_type", "CREDIT_CARD");
            put("amount", 13546);
        }};
    }

    public static Payment preparePayment(Order order) {
        return order.pay(paymentJsonForTest());
    }

    public static Map<String, Object> orderJsonForTest(long productId) {
        return new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("address", "beijing");
            put("phone", "65789");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", productId);
                put("quantity", 2);
            }}));
        }};
    }

    public static Order prepareOrder(User user, Product product) {
        return user.placeOrder(orderJsonForTest(product.getId()));
    }

    public static Map<String, Object> productJsonForTest() {
        return new HashMap<String, Object>() {{
            put("name", PRODUCT_NAME);
            put("description", PRODUCT_DESC);
            put("price", 1.1);
        }};
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        return productRepository.save(productJsonForTest());
    }
}
