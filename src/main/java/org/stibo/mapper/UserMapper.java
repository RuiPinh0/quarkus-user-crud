package org.stibo.mapper;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;

/**
 * UserMapper is a utility class that provides methods to convert between UserDTO and User entity.
 * It is used to map data between the DTO and entity layers.
 */
public class UserMapper {
    public static User newUserToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setStatus("active"); // Could be a enum here
        user.setEmail(userDTO.getEmail());
        return user;
    }
}