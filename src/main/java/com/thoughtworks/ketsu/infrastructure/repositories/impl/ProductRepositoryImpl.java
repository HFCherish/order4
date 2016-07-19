package com.thoughtworks.ketsu.infrastructure.repositories.impl;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProductMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    @Inject
    ProductMapper productMapper;

    @Override
    public Product save(Map<String, Object> prodInfo) {
        productMapper.save(prodInfo);
        return productMapper.findById(Long.valueOf(prodInfo.get("id").toString()));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMapper.findById(id));
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
}
