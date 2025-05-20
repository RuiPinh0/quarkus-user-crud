package org.stibo;

import java.net.URI;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;
import org.stibo.service.UserService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

/**
 * UserResource is a RESTful resource that handles user-related operations.
 * It provides endpoints to create, retrieve, and delete users.
 * The resource uses the UserService to perform the operations.
 */
@Path("/user")
public class UserResource {
    private static final Logger log = Logger.getLogger(UserResource.class); 

    @Inject
    UserService userService;
    
    // Get a user by id
    // The id is passed as a path parameter
    // The response is handled by the exception mapper
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        try{
           User user = userService.getUserById(id);
           return Response.ok(user).build();
        }catch(ClientErrorException e) {
            log.info(e.getMessage());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
        
    }

    // Create a new user from a DTO
    // The DTO is validated with @Valid anottation that has some defined constraints in the UserDTO class
    // The validation is done by the Quarkus framework and returns a 400 Bad Request if the validation fails
    // the response is handled by the exception mapper and could be improved in unvalid cases to return a more specific error message
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Valid UserDTO userDTO) {
        try{
            Long userId = userService.createUser(userDTO);
            return Response.created(URI.create("/user/" + userId)).build();
        }catch(ClientErrorException e) {
            log.info(e.getMessage());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    // Delete a user by id
    // The id is passed as a path parameter
    // The response is handled by the exception mapper
    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        try{
            return Response.ok(userService.deleteUser(id)).build();
        }catch(ClientErrorException e) {
            log.info(e.getMessage());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
