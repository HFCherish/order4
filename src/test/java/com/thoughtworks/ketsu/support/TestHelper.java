package com.thoughtworks.ketsu.support;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {
    public static final String PRODUCT_NAME = "Imran";
    public static final String PRODUCT_DESC = "teacher";
    public static final String USER_NAME = "Petrina";
    public static final String INVALID_USER_NAME = "JL';'";
    private static int auto_increment_key = 1;

    public static Map<String, Object> userJsonForTest(String name) {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
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
