package com.GoFit.Training.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "training")
    private List<TrainingHistory> trainingHistory = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "TrainingExercise",
            joinColumns = @JoinColumn(name = "traingingId"),
            inverseJoinColumns = @JoinColumn(name = "exerciseId"))
    @JsonIgnore
    @NotEmpty(message = "Exercises must be provided.") //@NotEmpty handle @NotNull
    private Set<Exercise> exercises = new HashSet<>();

    @Column(unique = true)
    @NotEmpty(message = "Training name must be provided.") //@NotEmpty handle @NotNull
    private String name;

    private Double duration;

    @NotEmpty(message = "Training type can not be empty.")
    private String type;

    @NotEmpty(message = "Target area can not be empty.")
    private String targetArea;

    @NotEmpty(message = "Video link can not be empty.")
    private String videoLink;

    @NotEmpty(message = "Cover image can not be empty.")
    private String coverImage;

    @NotEmpty(message = "Description can not be empty.")
    private String description;


    public Training() {
    }



    public Training(String name, Double duration, String type, String targetArea, String videoLink, String coverImage, String description) {
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.targetArea = targetArea;
        this.videoLink = videoLink;
        this.coverImage = coverImage;
        this.description = description;
    }

    public Training(List<TrainingHistory> trainingHistory, Set<Exercise> exercises, String name, Double duration, String type, String targetArea, String videoLink, String coverImage, String description) {
        this.trainingHistory = trainingHistory;
        this.exercises = exercises;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.targetArea = targetArea;
        this.videoLink = videoLink;
        this.coverImage = coverImage;
        this.description = description;
    }

    public Training(Set<Exercise> exercises, String name, Double duration, String type, String targetArea, String videoLink, String coverImage, String description) {
        this.exercises = exercises;
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.targetArea = targetArea;
        this.videoLink = videoLink;
        this.coverImage = coverImage;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TrainingHistory> getTrainingHistory() {
        return trainingHistory;
    }

    public void setTrainingHistory(List<TrainingHistory> trainingHistory) {
        this.trainingHistory = trainingHistory;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addTrainingHistory(TrainingHistory trainingHistory) {
        this.trainingHistory.add(trainingHistory);
    }

    public String getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(String targetArea) {
        this.targetArea = targetArea;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
