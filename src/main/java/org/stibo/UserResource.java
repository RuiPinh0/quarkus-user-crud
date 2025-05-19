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

@Path("/user")
public class UserResource {
    private static final Logger log = Logger.getLogger(UserResource.class); 

    @Inject
    UserService userService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) { //test this with a string
        try{
           User user = userService.getUserById(id);
           return Response.ok(user).build();
        }catch(ClientErrorException e) {
            log.info(e.getMessage());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
        
    }

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
