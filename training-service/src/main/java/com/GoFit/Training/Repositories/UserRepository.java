package com.GoFit.Training.Repositories;

import com.GoFit.Training.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findById(int id);

     Long deleteByEmail(String email);

     public User findByEmail(String email);
}
