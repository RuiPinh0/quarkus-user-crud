package org.stibo.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * GlobalExceptionMapper is a JAX-RS exception mapper that handles all uncaught exceptions
 * thrown by the application. It converts the exceptions into HTTP responses.   
 * This is useful for providing a consistent error response format.
 * The mapper can be used to log the exceptions, return custom error messages, or perform other actions.
 **/
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .entity(exception.getCause())
                       .build();
    }
}