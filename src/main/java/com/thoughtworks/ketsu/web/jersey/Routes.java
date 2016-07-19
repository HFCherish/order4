package com.thoughtworks.ketsu.web.jersey;

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
        return URI.create(baseUri + "users/");
    }

    public URI productUrl(Product product) {
        return URI.create(baseUri + "products/" + product.getId());
    }
}
