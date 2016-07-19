package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("products")
public class ProductApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Context UriInfo uriInfo) {
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id") Long id) {
        return new Product("hhk", "hkj",798);
    }
}
