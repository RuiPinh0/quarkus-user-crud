package org.stibo.mapper;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;

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