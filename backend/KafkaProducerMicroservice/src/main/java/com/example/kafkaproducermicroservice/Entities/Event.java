package com.example.kafkaproducermicroservice.Entities;

import java.io.Serial;
import java.util.Date;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String country;
    private String type;
    private String description;
    private String statusOfImportance;
    private Date time;
    private Double actual;
    private Double forecast;
    private Double previous;

    public Event(String id, String name, String country, String type, String description, String statusOfImportance, Date time, Double actual, Double forecast, Double previous) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.type = type;
        this.description = description;
        this.statusOfImportance = statusOfImportance;
        this.time = time;
        this.actual = actual;
        this.forecast = forecast;
        this.previous = previous;
    }

    // Getters and setters for each field

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusOfImportance() {
        return statusOfImportance;
    }

    public void setStatusOfImportance(String statusOfImportance) {
        this.statusOfImportance = statusOfImportance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getForecast() {
        return forecast;
    }

    public void setForecast(Double forecast) {
        this.forecast = forecast;
    }

    public Double getPrevious() {
        return previous;
    }

    public void setPrevious(Double previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", statusOfImportance='" + statusOfImportance + '\'' +
                ", time=" + time +
                ", actual=" + actual +
                ", forecast=" + forecast +
                ", previous=" + previous +
                '}';
    }
}
