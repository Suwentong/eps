package com.company;

import com.company.user.Role;
import com.company.user.User;
import com.company.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailTest() {
        Optional<User> users = userRepository.findByEmail("abdukadyrovs@mail.com");
        Assertions.assertEquals("abdukadyrovs@mail.com", users.stream().findAny().get().getEmail());
    }
    @Test
    void findAllByUsernameTest() {
        List<User> users = userRepository.findAllByUsername("Sulton 苏文通");
        Assertions.assertEquals("Sulton 苏文通", users.stream().findAny().get().getUsername());
    }

    @Test
    void findAllByStudentIdTest() {
        List<User> users = userRepository.findAllByStudentId("19160079");
        Assertions.assertEquals("Sulton 苏文通", users.stream().findAny().get().getUsername());
    }

    @Test
    void findAllByDepartmentTest() {
        List<User> users = userRepository.findAllByDepartment("计算机学院");
        Assertions.assertEquals("Sulton 苏文通", users.stream().findAny().get().getUsername());
    }

    @Test
    void findAllByRoleTest() {
        List<User> users = userRepository.findAllByRole(Role.ADMIN);
        Assertions.assertEquals("admin", users.stream().findAny().get().getUsername());
    }
}
