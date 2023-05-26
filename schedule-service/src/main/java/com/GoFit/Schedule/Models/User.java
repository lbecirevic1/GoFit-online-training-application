package com.GoFit.Schedule.Models;

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


    private String name;


    // private String lastName;


    // private String username;


    // private String password;


    private String email;

   /* @NotNull(message = "User must have a birthday")
    private Date birthday;*/

    public User() {}
    /*
    public User(String name, String lastName, String username, String password, String email/*, Date birthday*///)// {
   /*     this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
       // this.birthday = birthday;
    }*/



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
}