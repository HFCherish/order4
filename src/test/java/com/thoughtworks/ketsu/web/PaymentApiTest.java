package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport {
    private String paymentBaseUrl;
    private User user;
    private Product product;
    private Order order;

    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        user = prepareUser(userRepository);
        product = prepareProduct(productRepository);
        order = prepareOrder(user, product);
        paymentBaseUrl = "users/" + user.getId() + "/orders/" + order.getId() + "/payment";
    }

    @Test
    public void should_pay_successfully() {

        Response response = post(paymentBaseUrl, paymentJsonForTest());

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_payment_successfully() {
        Payment payment = preparePayment(order);

        Response response = get(paymentBaseUrl);

        assertThat(response.getStatus(), is(200));
        Map paymentInfo = response.readEntity(Map.class);
        assertThat(paymentInfo.get("pay_type"), is(payment.getType().name()));
        assertThat((double)paymentInfo.get("amount"), is(payment.getAmount()));
        assertThat(new DateTime(paymentInfo.get("created_at")), is(payment.getCreatedAt()));
        assertThat(paymentInfo.get("order_uri"), is("users/" + user.getId() + "/orders/" + order.getId()));
        assertThat(paymentInfo.get("uri"), is(paymentBaseUrl));

    }

    @Test
    public void should_404_when_get_payment_given_not_pay() {
        Response response = get(paymentBaseUrl);

        assertThat(response.getStatus(), is(404));

    }
}
