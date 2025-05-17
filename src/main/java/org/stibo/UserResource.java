package org.stibo;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser() {
        return userService.getUser();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(String user) {
        // Logic to create a user
        return "{\"status\":\"User created\"}";
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("id") String id, String user) {
        // Logic to update a user
        return "{\"status\":\"User updated\"}";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("id") String id) {
        // Logic to delete a user
        return "{\"status\":\"User deleted\"}";
    }
}
