package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    private User user;
    private Product product;
    private String ordersBaseUrl;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        ordersBaseUrl = "/users/" + user.getId() + "/orders";
    }

    @Test
    public void should_create_order_successfully() {
        Response response = post(ordersBaseUrl, orderJsonForTest(product.getId()));

        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toString(), containsString(ordersBaseUrl));
        assertThat(response.getLocation().toString().matches(".*/\\d+$"), is(true));
    }

    @Test
    public void should_400_when_order_input_is_invalid() {
        //name empty
        Response response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("address", "beijing");
            put("phone", "65789");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", product.getId());
                put("quantity", 2);
            }}));
        }});

        assertThat(response.getStatus(), is(400));

        //address empty
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("phone", "65789");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", product.getId());
                put("quantity", 2);
            }}));
        }});

        assertThat(response.getStatus(), is(400));

        //phone empty
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("address", "beijing");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", product.getId());
                put("quantity", 2);
            }}));
        }});

        assertThat(response.getStatus(), is(400));

        //order items empty
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("phone", "65789");
            put("address", "beijing");
        }});

        assertThat(response.getStatus(), is(400));

        //order items 0 item
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("phone", "65789");
            put("address", "beijing");
            put("order_items", new ArrayList<>());
        }});

        assertThat(response.getStatus(), is(400));

        //order items product_id empty
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("phone", "65789");
            put("address", "beijing");
            put("order_items", Arrays.asList(new HashMap(){{
                put("quantity", 2);
            }}));
        }});

        assertThat(response.getStatus(), is(400));

        //order items quantity empty
        response = post(ordersBaseUrl, new HashMap<String, Object>() {{
            put("name", USER_NAME);
            put("phone", "65789");
            put("address", "beijing");
            put("order_items", Arrays.asList(new HashMap(){{
                put("product_id", product.getId());
            }}));
        }});

        assertThat(response.getStatus(), is(400));
    }
}
