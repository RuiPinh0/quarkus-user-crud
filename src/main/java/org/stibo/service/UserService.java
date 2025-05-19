package org.stibo.service;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;

import jakarta.ws.rs.ClientErrorException;

// I think that this interface is overengeneered and not needed for this complexity and special case
//but in order of adhering strictly to the Dependency Inversion Principle I will keep it
public interface UserService {
    User getUserById(Long id) throws ClientErrorException;   
    Long createUser(UserDTO user) throws ClientErrorException;
    Boolean deleteUser(Long id) throws ClientErrorException; 
}
