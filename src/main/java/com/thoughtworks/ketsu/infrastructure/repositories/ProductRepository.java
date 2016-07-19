package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {
    Product save(Map<String, Object> prodInfo);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
