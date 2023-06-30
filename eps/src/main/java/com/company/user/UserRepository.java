package com.company.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findAllByUsername(String username);

    List<User> findAllByStudentId(String studentId);

    List<User> findAllByDepartment(String department);

    List<User> findAllByRole(Role user);

    User findByUsername(String username);
}
