package org.launchcode.caninecoach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Details extends AbstractEntity{
    @NotBlank
    private String length;
    @NotBlank
    private String description;


    private Integer price;

    @JoinColumn(name = "details_id")
    private ArrayList<Course> courses = new ArrayList<>();

    public Details() {
    }

    public Details(String length, String description, Integer price) {
        this.length = length;
        this.description = description;
        this.price = price;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
