package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.ketsu.support.TestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentOperationTest {

    @Inject
    UserRepository userRepository;
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_pay_and_get_payment() {
        Order order = prepareOrder(prepareUser(userRepository), prepareProduct(productRepository));

        Payment payment = order.pay(paymentJsonForTest());
        Optional<Payment> fetched = order.getPayment();

        assertThat(fetched.isPresent(), is(true));
        assertThat(fetched.get().getOrderId(), is(order.getId()));
    }
}
