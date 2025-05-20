package org.stibo.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.stibo.dto.UserDTO;
import org.stibo.entity.User;

public class UserMapperTest {

    @Test
    void testNewUserToEntity() {
        UserDTO dto = new UserDTO();
        dto.setUsername("username");
        dto.setFirstName("Map");
        dto.setLastName("User");
        dto.setPhone("123");
        dto.setAddress("Street");
        dto.setEmail("user@example.com");

        User user = UserMapper.newUserToEntity(dto);

        Assertions.assertEquals("username", user.getUsername());
        Assertions.assertEquals("Map", user.getFirstName());
        Assertions.assertEquals("User", user.getLastName());
        Assertions.assertEquals("123", user.getPhone());
        Assertions.assertEquals("Street", user.getAddress());
        Assertions.assertEquals("user@example.com", user.getEmail());
        Assertions.assertEquals("active", user.getStatus());
    }

    @Test
    void testNewUserToEntity_Null() {
        User user = UserMapper.newUserToEntity(null);
        Assertions.assertNull(user);
    }
}