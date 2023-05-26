package com.GoFit.DietPlan.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Description must be entered.")
    @Size(min = 2, max=100,message = "Length must be at least 2 chars long.")
    private String description;

    @NotNull(message = "Recepies must be entered.")
    @Size(min = 2, max=1000,message = "Length must be at least 2 chars long.")
    private String recepies;

    private String imagePath;

    @OneToOne
    @NotNull
    @JoinColumn(name = "conditionId", nullable = false)
    private PhysicalCondition condition;

    private Boolean deleted=false;

    public DietPlan(String description, String recepies, PhysicalCondition condition,Boolean deleted) {
        this.description = description;
        this.recepies = recepies;
        this.condition = condition;
        this.deleted=deleted;
    }

    public DietPlan(String description, String recepies, String imagePath, PhysicalCondition condition, Boolean deleted) {
        this.description = description;
        this.recepies = recepies;
        this.imagePath = imagePath;
        this.condition = condition;
        this.deleted = deleted;
    }

    public DietPlan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecepies() {
        return recepies;
    }

    public void setRecepies(String recepies) {
        this.recepies = recepies;
    }

    public PhysicalCondition getCondition() {
        return condition;
    }

    public void setCondition(PhysicalCondition condition) {
        this.condition = condition;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}