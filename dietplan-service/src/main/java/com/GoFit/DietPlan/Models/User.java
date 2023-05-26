package com.GoFit.DietPlan.Models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Athlete")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name must be entered.")
    @Size(min = 2, max=100,message = "Length must be at least 2 chars long.")
    private String name;

    @Email
    @NotNull(message = "Email must be entered.")
    @Size(min = 2, max=100,message = "Length must be at least 2 chars long.")
    private String email;

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}