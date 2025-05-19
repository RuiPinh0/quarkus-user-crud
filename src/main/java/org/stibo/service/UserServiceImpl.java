package org.stibo.service;

import org.stibo.dto.UserDTO;
import org.stibo.entity.User;
import org.stibo.mapper.UserMapper;
import org.stibo.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final String USER_NOT_FOUND = "User not found";
    private final String USER_ID_INVALID = "User ID cannot be null and must be a number higger than 1";
    private final String USER_NULL = "User cannot be null or empty";

    @Inject
    UserRepository userRepository;
 
    @Override
    public User getUserById(Long id) throws ClientErrorException {
        User user;
        if(id == null || id <= 0) {
            throw new BadRequestException(USER_ID_INVALID);
        } 
        user = userRepository.findById(id);

        if(user == null) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public Long createUser(UserDTO userDTO) throws ClientErrorException {
        if(userDTO == null) {
            throw new BadRequestException(USER_NULL);
        } 
        // Map DTO to Entity, could be done with a mapper library like MapStruct or a builder pattern for more complex cases
        // but for simplicity I will do it with a custom mapper
        User user = UserMapper.newUserToEntity(userDTO);
        userRepository.persist(user);
        return user.getId();
    }

    @Override
    public Boolean deleteUser(Long id) throws ClientErrorException {
        if(id == null || id <= 0) {
            throw new BadRequestException(USER_ID_INVALID);
        } 
        return userRepository.deleteById(id);
    }
}
