package org.stibo.repository;

import org.stibo.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * UserRepository is a Panache repository for the User entity.
 * It provides methods to perform CRUD operations on the User entity.
 */
@ApplicationScoped
public class UserRepository  implements PanacheRepository<User> {
}
