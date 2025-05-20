package org.stibo.mapper;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GlobalExceptionMapperTest {

    @Test
    void testToResponse() {
        GlobalExceptionMapper mapper = new GlobalExceptionMapper();
        Exception ex = new Exception("Something went wrong");
        Response response = mapper.toResponse(ex);

        Assertions.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        Assertions.assertNull(response.getEntity());
    }
}
