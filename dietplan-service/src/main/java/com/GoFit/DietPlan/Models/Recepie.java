package com.GoFit.DietPlan.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recepie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Subject must be entered.")
    @Size(min = 2, max=100,message = "Length must be at least 2 chars long.")
    private String subject;

    @NotNull(message = "Recipe must be entered.")
    @Size(min = 2, max=1000,message = "Length must be at least 2 chars long.")
    private String RecepieText;

    @NotNull(message = "Group must be entered.")
    private Integer dedicated;

    private String imagePath;

    public Recepie(String subject, String recepieText, Integer dedicated, String imagePath) {
        this.subject = subject;
        RecepieText = recepieText;
        this.dedicated = dedicated;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Recepie(String subject, String recepieText, Integer dedicated) {
        this.subject = subject;
        RecepieText = recepieText;
        this.dedicated = dedicated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecepieText() {
        return RecepieText;
    }

    public void setRecepieText(String recepieText) {
        RecepieText = recepieText;
    }

    public Integer getDedicated() {
        return dedicated;
    }

    public void setDedicated(Integer dedicated) {
        this.dedicated = dedicated;
    }
}
