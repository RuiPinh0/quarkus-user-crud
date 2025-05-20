package org.stibo.service;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;

import jakarta.ws.rs.ClientErrorException;

/**
 * UserService is an interface that defines the contract for user-related operations.
 * It provides methods to create, retrieve, and delete users.
 */
public interface UserService {
    User getUserById(Long id) throws ClientErrorException;   
    Long createUser(UserDTO user) throws ClientErrorException;
    Boolean deleteUser(Long id) throws ClientErrorException; 
}
