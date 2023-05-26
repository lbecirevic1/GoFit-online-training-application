package com.GoFit.SystemEvent.DietPlan.Model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "timestamp", nullable = true, updatable = false)
    private String timestamp;

    @NotNull
    @NotBlank
    private String microservice;

    @NotNull
    @NotBlank
    private String actionType;

    @NotNull
    @NotBlank
    private String resourceName;

    @NotNull
    @NotBlank
    private String responseType;

    /*@NotNull
    @NotBlank
    @Column(name = "user_info", nullable = true, updatable = false)
    private String username; */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMicroservice() {
        return microservice;
    }

    public void setMicroservice(String microservice) {
        this.microservice = microservice;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
