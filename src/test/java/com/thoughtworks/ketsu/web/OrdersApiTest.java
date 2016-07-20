package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.OrderItem;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.*;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrdersApiTest extends ApiSupport {
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

    @Test
    public void should_get_order_successfully() {
        Order order = prepareOrder(user, product);
        String getOneUrl = ordersBaseUrl + "/" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        assertThat(orderInfo.get("uri").toString(), containsString(getOneUrl));
        assertThat(orderInfo.get("name"), is(order.getName()));
        assertThat(orderInfo.get("address"), is(order.getAddress()));
        assertThat(orderInfo.get("phone"), is(order.getPhone()));
        assertThat(orderInfo.get("total_price"), is(order.getTotalPrice()));
        assertThat(new DateTime(orderInfo.get("created_at")), is(order.getCreatedAt()));

        List orderItems = (List)orderInfo.get("order_items");
        assertThat(orderItems.size(), is(1));
        Map fetchedItem = (Map)orderItems.get(0);
        OrderItem item = order.getOrderItems().get(0);
        assertThat(Long.valueOf(fetchedItem.get("product_id").toString()), is(item.getProductId()));
        assertThat(fetchedItem.get("quantity"), is(item.getQuantity()));
        assertThat((double)fetchedItem.get("amount"), is(item.getAmount()));
    }

    @Test
    public void should_404_when_get_order_given_invalid_orderId() {
        Order order = prepareOrder(user, product);
        String getOneUrl = ordersBaseUrl + "/9" + order.getId();

        Response response = get(getOneUrl);

        assertThat(response.getStatus(), is(404));

    }

    @Test
    public void should_get_all_orders_successfully() {
        Order order = prepareOrder(user, product);

        Response response = get(ordersBaseUrl);

        assertThat(response.getStatus(), is(200));
        List orders = response.readEntity(List.class);
        assertThat(orders.size(), is(1));

    }
}
