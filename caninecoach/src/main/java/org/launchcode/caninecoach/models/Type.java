package org.launchcode.caninecoach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

@Entity
public class Type extends AbstractEntity {

    private String name;


    public Type(String name) {
        this.name = name;
    }

    @ManyToMany
    @NotNull
    @JoinTable(name = "course_info", joinColumns = { @JoinColumn(name = "type_id")}, inverseJoinColumns = { @JoinColumn(name = "course_id")})
    private final ArrayList<Course> courses = new ArrayList<>();

    public Type() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
