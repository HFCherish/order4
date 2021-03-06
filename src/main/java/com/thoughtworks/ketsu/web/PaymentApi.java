package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

public class PaymentApi {
    private Order order;

    public PaymentApi(Order order) {
        this.order = order;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map paymentInfo,
                        @Context UriInfo uriInfo) {
        order.pay(paymentInfo);
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaymentResponseBean getPayment(@Context UriInfo uriInfo) {
        return new PaymentResponseBean(order.getPayment()
                .map(payment -> payment)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)), uriInfo);
    }
}
