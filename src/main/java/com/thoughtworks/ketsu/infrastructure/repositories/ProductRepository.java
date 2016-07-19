package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Product;

import java.util.Map;

public interface ProductRepository {
    Product save(Map<String, Object> prodInfo);
}
