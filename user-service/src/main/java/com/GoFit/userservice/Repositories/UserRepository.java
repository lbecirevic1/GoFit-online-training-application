package com.GoFit.userservice.Repositories;

import com.GoFit.userservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
    User findUserByPassword(String password);

}
