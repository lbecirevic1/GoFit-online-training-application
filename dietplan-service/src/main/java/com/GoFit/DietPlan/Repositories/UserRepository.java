package com.GoFit.DietPlan.Repositories;

import com.GoFit.DietPlan.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository <User, Integer>{

    User getById(int id);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String mail);

    User findByName(String ime);

    User deleteByEmail(String email);

}