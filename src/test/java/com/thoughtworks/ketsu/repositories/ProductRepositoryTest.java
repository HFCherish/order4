package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.prepareProduct;
import static com.thoughtworks.ketsu.support.TestHelper.productJsonForTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_get_product() {
        Map<String, Object> prodInfo = productJsonForTest();
        Product product = productRepository.save(prodInfo);
        Optional<Product> fetched = productRepository.findById(Long.valueOf(prodInfo.get("id").toString()));

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getId(), is(product.getId()));
    }

    @Test
    public void should_get_all_products() {
        Product product = prepareProduct(productRepository);

        List<Product> fetched = productRepository.findAll();

        assertThat(fetched.size(), is(1));
        assertThat(fetched.get(0).getId(), is(product.getId()));
    }
}
