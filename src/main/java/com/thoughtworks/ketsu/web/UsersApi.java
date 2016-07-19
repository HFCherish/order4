package com.thoughtworks.ketsu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser() {
        return Response.created(URI.create("")).build();
    }

//    @Path("{userId}")
//    public UserApi getUser(@PathParam("userId") String userId,
//                           @Context UserRepository userRepository) {
//        return userRepository.ofId(new UserId(userId))
//                .map(UserApi::new)
//                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
//    }
}
