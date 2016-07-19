package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

@Path("users")
public class UsersApi {
    @Context
    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> userInfo,
                               @Context Routes routes) {
        if( userInfo.get("name")==null || !userInfo.get("name").toString().matches("^[A-Za-z\\d]+$")) {
            throw new IllegalArgumentException("must contains name, which composed of letters and numbsers.");
        }
        return Response.created(routes.userUrl(userRepository.save(userInfo))).build();
    }

    @Path("{userId}")
    public UserApi getUser(@PathParam("userId") long userId) {
        return userRepository.findById(userId)
                .map(UserApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
