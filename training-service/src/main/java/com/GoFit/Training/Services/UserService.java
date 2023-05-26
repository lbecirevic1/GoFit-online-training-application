package com.GoFit.Training.Services;


import com.GoFit.Training.Exceptions.NotFoundException;
import com.GoFit.Training.Models.User;
import com.GoFit.Training.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User register(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(User user) {
        userRepository.deleteByEmail(user.getEmail());
        return "User removed.";
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
