package com.GoFit.userservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Athlete")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must be provided.") //name should not be null or empty
    @Size(min = 2, message = "Name should have at least 2 characters.")
    private String name;

    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "Username must be provided.")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
            message = "Username can only contain letters and digits and must contain 6 to 12 characters.")
    private String username;

    @Column
    @NotEmpty(message = "Password must be provided.")
//    @Pattern(regexp = "^(?=.*\\d).{8,20}$",
//        message = "Password must contain between 8 and 20 characters.")
    private String password;


    @Column(unique = true)
    @Email(message = "Invalid email address")
    private String email;

    @Column
    private Integer age;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String lastName, String username, String password, String email, Integer age, List<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public User(String name, String lastName, String username, String password, String email, Integer age) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPassword(String password) {
            this.password = password;
        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoleNames() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < this.getRoles().size(); i++) {
            list.add(this.getRoles().get(i).getName());
        }
        return list;
    }


}
