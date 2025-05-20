package org.stibo.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.stibo.entity.User;

import java.util.List;

@QuarkusTest
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    @Transactional
    public void testPersistAndFindById() {
        User user = new User();
        user.setUsername("repo_testuser");
        user.setFirstName("Repo");
        user.setLastName("Test");
        user.setEmail("repo_testuser@example.com");

        userRepository.persist(user);
        Assertions.assertNotNull(user.getId());

        User found = userRepository.findById(user.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals("repo_testuser", found.getUsername());
    }

    @Test
    @Transactional
    public void testDeleteById() {
        User user = new User();
        user.setUsername("repo_deluser");
        user.setFirstName("Repo");
        user.setLastName("Delete");
        user.setEmail("repo_deluser@example.com");

        userRepository.persist(user);
        Long id = user.getId();
        Assertions.assertTrue(userRepository.deleteById(id));
        Assertions.assertNull(userRepository.findById(id));
    }

    @Test
    @Transactional
    public void testListAll() {
        User user1 = new User();
        user1.setUsername("repo_list1");
        user1.setFirstName("Repo");
        user1.setLastName("List");
        user1.setEmail("repo_list1@example.com");

        User user2 = new User();
        user2.setUsername("repo_list2");
        user2.setFirstName("Repo");
        user2.setLastName("List");
        user2.setEmail("repo_list2@example.com");

        userRepository.persist(user1);
        userRepository.persist(user2);

        List<User> users = userRepository.listAll();
        Assertions.assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("repo_list1")));
        Assertions.assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("repo_list2")));
    }
}