package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.infrastructure.repositories.ProductRepository;
import com.thoughtworks.ketsu.infrastructure.validators.ProductValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

@Path("products")
public class ProductApi {
    @Context
    ProductRepository productRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> prodInfo,
                           @Context UriInfo uriInfo) {
        new ProductValidator().validate(prodInfo);
        productRepository.save(prodInfo);
        return Response.created(uriInfo.getRequestUri()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("id") Long id) {
        return productRepository.findById(id).map(product -> product).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
