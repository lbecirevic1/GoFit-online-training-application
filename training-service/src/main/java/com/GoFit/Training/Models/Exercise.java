package com.GoFit.Training.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            }, mappedBy = "exercises")
    private Set<Training> trainings = new HashSet<>();

    //VALIDATION
    @NotEmpty(message = "Name must be provided.")
    private String name;

    @NotNull(message = "Duration may not be null.")
    private Double duration;

//    private String videoLink;

    @NotEmpty(message = "Exercise description must be provided.")
    @Size(min = 10, max = 1000, message = "Please provide min 10 and max 1000 characters.")
    private String description;

    @NotEmpty(message = "Image link for an exercise must be provided.")
    private String image;


    public Exercise() {
    }

    public Exercise(String name, Double duration, String description, String image) {
        this.name = name;
        this.duration = duration;
//        this.videoLink = videoLink;
        this.description = description;
        this.image = image;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void addTraining(Training training) {
        this.trainings.add(training);
    }
}
