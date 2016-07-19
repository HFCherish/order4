package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductApiTest extends ApiSupport {

    private String productBaserUrl;

    @Inject
    ProductRepository productRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        productBaserUrl = "/products";
    }

    @Test
    public void should_create_product() {
        Response response = post(productBaserUrl, productJsonForTest());

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(productBaserUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_create_product_given_incomplete_param() {
        Response response = post(productBaserUrl, new HashMap<String, Object>() {{
            put("name", PRODUCT_NAME);
            put("description", PRODUCT_DESC);
        }});

        assertThat(response.getStatus(), is(400));

        response = post(productBaserUrl, new HashMap<String, Object>() {{
            put("name", PRODUCT_NAME);
            put("price", 1.1);
        }});

        assertThat(response.getStatus(), is(400));

        response = post(productBaserUrl, new HashMap<String, Object>() {{
            put("description", PRODUCT_DESC);
            put("price", 1.1);
        }});

        assertThat(response.getStatus(), is(400));

    }

    @Test
    public void should_get_ond_product_successfully() {
        Product product = prepareProduct(productRepository);
        String getOneUrl = productBaserUrl + "/" + product.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map fetchedInfo = response.readEntity(Map.class);
        assertThat(fetchedInfo.get("uri").toString(), containsString(getOneUrl));
        assertThat(Long.valueOf(fetchedInfo.get("id").toString()), is(product.getId()));
        assertThat(fetchedInfo.get("name").toString(), is(product.getName()));
        assertThat(fetchedInfo.get("description").toString(), is(product.getDescription()));
        assertThat((double)fetchedInfo.get("price"), is(product.getPrice()));
    }

    @Test
    public void should_404_when_get_one_product_given_invalid_id() {
        String getOneUrl = productBaserUrl + "/" + 0;

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_get_all_products_successfully() {

        Response response = get(productBaserUrl);

        assertThat(response.getStatus(), is(200));

    }
}
