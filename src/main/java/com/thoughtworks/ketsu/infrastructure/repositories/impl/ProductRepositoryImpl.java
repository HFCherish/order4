package com.thoughtworks.ketsu.infrastructure.repositories.impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product save(Map<String, Object> prodInfo) {
        return new Product("gjy","hukhk",324.0);
    }
}
