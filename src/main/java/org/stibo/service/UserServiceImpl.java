package org.stibo.service;

import org.jboss.logging.Logger;
import org.stibo.dto.UserDTO;
import org.stibo.entity.User;
import org.stibo.mapper.UserMapper;
import org.stibo.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;

/**
 * UserServiceImpl is the implementation of the UserService interface.
 * It provides methods to create, retrieve, and delete users.
 * The service uses the UserRepository to perform the operations on the database.
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class); 

    private final String USER_NOT_FOUND = "User not found";
    private final String USER_ID_INVALID = "User ID cannot be null and must be a number higger than 1";
    private final String USER_NULL = "User cannot be null or empty";

    @Inject
    UserRepository userRepository;
 
    /**
     * Retrieves a user by its ID.
     * @param id the ID of the user to retrieve
     * @return the User object if found
     * @throws BadRequestException if the user ID is invalid
     * @throws NotFoundException if the user is not found
     */
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

    /**
     * Creates a new user from the provided UserDTO.
     * @param userDTO the UserDTO object containing user data
     * @return the ID of the created user
     * @throws ClientErrorException if the userDTO is null or invalid
     */
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

    /**
     * Deletes a user by its ID.
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false otherwise
     * @throws ClientErrorException if the ID is invalid
     */
    @Override
    public Boolean deleteUser(Long id) throws ClientErrorException {
        if(id == null || id <= 0) {
            throw new BadRequestException(USER_ID_INVALID);
        } 
        return userRepository.deleteById(id);
    }
}
