package org.launchcode.caninecoach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Details extends AbstractEntity{
    @NotBlank
    private String length;
    @NotBlank
    private String description;


    private Integer price;

    @OneToOne
    @NotNull
    @JoinColumn(name = "course_id")
    private Course course;

    public Details() {
    }

    public Details(String length, String description, Integer price, Course course) {
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

    public Course getCourse() {
        return course;
    }
}
