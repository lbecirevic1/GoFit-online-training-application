package com.GoFit.Training.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class TrainingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainingId", referencedColumnName = "id")
    @JsonIgnore
    private Training training;

    private Double vote;

    public TrainingHistory() {
    }

    public TrainingHistory(Double vote) {
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

}
