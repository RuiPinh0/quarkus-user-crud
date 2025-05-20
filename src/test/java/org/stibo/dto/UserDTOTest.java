package org.stibo.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class UserDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidUserDTO() {
        UserDTO dto = new UserDTO();
        Set violations = validator.validate(dto);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    void testValidUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setUsername("validuser");
        dto.setFirstName("Valid");
        dto.setLastName("User");
        dto.setEmail("validuser@example.com");
        
        Set violations = validator.validate(dto);
        Assertions.assertTrue(violations.isEmpty());
    }
    

    @Test
    void testInvalidEmail() {
        UserDTO dto = new UserDTO();
        dto.setUsername("validuser");
        dto.setFirstName("Valid");
        dto.setLastName("User");
        dto.setEmail("invalid-mail");
        
        Set violations = validator.validate(dto);
        Assertions.assertFalse(violations.isEmpty());
    }
}

