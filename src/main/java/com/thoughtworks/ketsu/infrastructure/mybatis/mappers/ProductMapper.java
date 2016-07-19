package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ProductMapper {
    void save(@Param("info") Map<String, Object> prodInfo);

    Product findById(@Param("id") Long id);
}
