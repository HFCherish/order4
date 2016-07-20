package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OrdersApi {
    private User user;

    public OrdersApi(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(@Context Routes routes) {
        return Response.created(routes.orderUrl(new Order(user.getId()))).build();
    }
}
