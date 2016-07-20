package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay() {
        return Response.created(URI.create("")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Payment getPayment() {
        return order.getPayment()
                .map(payment -> payment)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
