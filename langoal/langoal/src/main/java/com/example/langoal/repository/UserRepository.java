package com.example.langoal.repository;

import com.example.langoal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserById (long kw);
    List<User> findUserByEmail(String email);
    List<User> findUserByPassword(String password);
}
