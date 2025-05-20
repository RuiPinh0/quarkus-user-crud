package org.stibo.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.stibo.dto.UserDTO;
import org.stibo.entity.User;
import org.stibo.repository.UserRepository;


@QuarkusTest
public class UserServiceImplTest {

    @Inject
    UserServiceImpl userServiceImpl;

    @Inject
    UserRepository userRepository;

    @Test
    @TestTransaction
    void testCreateUser_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("testuser");
        dto.setFirstName("Test");
        dto.setLastName("User");
        dto.setEmail("test@example.com");

        Long id = userServiceImpl.createUser(dto);
        Assertions.assertNotNull(id);
    }

    @Test
    @TestTransaction
    void testCreateUser_NullDTO() {
        Assertions.assertThrows(BadRequestException.class, () -> userServiceImpl.createUser(null));
    }

    @Test
    @TestTransaction
    void testGetUserById_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("getuser");
        dto.setFirstName("Get");
        dto.setLastName("User");
        dto.setEmail("getuser@example.com");
        Long id = userServiceImpl.createUser(dto);

        User user = userServiceImpl.getUserById(id);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("getuser", user.getUsername());
    }

    @Test
    @TestTransaction
    void testGetUserById_NotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> userServiceImpl.getUserById(99999L));
    }

    @Test
    void testGetUserById_InvalidId() {
        Assertions.assertThrows(BadRequestException.class, () -> userServiceImpl.getUserById(0L));
        Assertions.assertThrows(BadRequestException.class, () -> userServiceImpl.getUserById(null));
    }

    @Test
    @TestTransaction
    void testDeleteUser_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("deluser");
        dto.setFirstName("Del");
        dto.setLastName("User");
        dto.setEmail("deluser@example.com");
        Long id = userServiceImpl.createUser(dto);

        Boolean deleted = userServiceImpl.deleteUser(id);
        Assertions.assertTrue(deleted);
    }

    @Test
    void testDeleteUser_InvalidId() {
        Assertions.assertThrows(BadRequestException.class, () -> userServiceImpl.deleteUser(0L));
        Assertions.assertThrows(BadRequestException.class, () -> userServiceImpl.deleteUser(null));
    }
}