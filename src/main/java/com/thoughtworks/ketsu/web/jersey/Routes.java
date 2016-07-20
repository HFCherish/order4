package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.Order;
import com.thoughtworks.ketsu.domain.Product;
import com.thoughtworks.ketsu.domain.user.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI userUrl(User user) {
        return URI.create(baseUri + "users/" + user.getId());
    }

    public URI productUrl(Product product) {
        return URI.create(baseUri + "products/" + product.getId());
    }

    public URI orderUrl(Order order) {
        return URI.create(baseUri + "users/" + order.getUserId() + "/orders/" + order.getId());
    }
}
