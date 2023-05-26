package com.GoFit.Schedule.Repositories;

import com.GoFit.Schedule.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository <User, Integer>{

    User getById(int id);

    Optional<User> findById(Integer id);

    User findByName(String ime);
    User findByEmail(String ime);

    //  @Query(value="SELECT * FROM ATHLETE WHERE displayName like '%name%';",nativeQuery = true)
    //   User findByUsername(String ime);

    //   List<User> findByLastName(String prezime);

    User deleteByEmail(String email);

}