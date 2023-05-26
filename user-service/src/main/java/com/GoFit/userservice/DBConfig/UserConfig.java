package com.GoFit.userservice.DBConfig;

import com.GoFit.userservice.Models.Role;
import com.GoFit.userservice.Models.User;
import com.GoFit.userservice.Repositories.RoleRepository;
import com.GoFit.userservice.Repositories.UserRepository;
import com.GoFit.userservice.Services.LoginService;
import com.GoFit.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class UserConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginService loginService;

    @Bean
    CommandLineRunner userCommandLineRunner() {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            User user1 = new User("Admin", "admin", "aadminnn", "password", "admin@gmail.com", 24, new ArrayList<>());
            User user2 = new User("Korisnik", "korisnik", "kkornisnik", "password", "korisnik@gmail.com", 25, new ArrayList<>());
            User user3 = new User("Lejla", "Becirevic", "lbecirevic", "password", "lejla@gmail.com", 24, new ArrayList<>());
            loginService.addAdmin(user1);
            loginService.register(user2);
            loginService.register(user3);
        };
    }

}
