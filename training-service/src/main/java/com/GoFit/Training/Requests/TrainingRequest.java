package com.GoFit.Training.Requests;

import com.GoFit.Training.Models.Exercise;

import java.util.List;
import java.util.Set;

public class TrainingRequest {
    //Specify which parameters user needs to pass
    public Double duration;
    public String name;
    public String type;
    public String targetArea;
    public String videoLink;
    public String coverImage;
    public String description;
    public List<Integer> exercises;

    public TrainingRequest(Double duration, String name, String type, String targetArea, String videoLink, String coverImage, String description, List<Integer> exercises) {
        this.duration = duration;
        this.name = name;
        this.type = type;
        this.targetArea = targetArea;
        this.videoLink = videoLink;
        this.coverImage = coverImage;
        this.description = description;
        this.exercises = exercises;
    }
}
