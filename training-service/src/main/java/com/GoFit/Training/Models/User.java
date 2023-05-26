package com.GoFit.Training.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Athlete")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "user")
    private Set<TrainingHistory> trainingHistory = new HashSet<>();

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private Double trainingLevel;


    public User() {
    }


    public User(String name, String email, Double trainingLevel) {
        this.name = name;
        this.email = email;
        this.trainingLevel = trainingLevel;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Double getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(Double trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Set<TrainingHistory> getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(Set<TrainingHistory> trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public void addTrainingHistory(TrainingHistory trainingHistory) {
        this.trainingHistory.add(trainingHistory);
    }
}
