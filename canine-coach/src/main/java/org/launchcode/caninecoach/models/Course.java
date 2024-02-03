package org.launchcode.caninecoach.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
public class Course extends AbstractEntity{

    @ManyToMany
    @NotNull
    @JoinTable(name = "course_info", joinColumns = { @JoinColumn(name = "course_id")}, inverseJoinColumns = { @JoinColumn(name = "type_id")})
    private ArrayList<Type> type = new ArrayList<>();

    @OneToOne
    @NotNull
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    public Course(ArrayList<Type> type, Details details, Curriculum curriculum) {
        this.type = type;
        this.details = details;
        this.curriculum = curriculum;
    }

    public Course() {

    }

    public ArrayList<Type> getType() {
        return type;
    }

    public void setType(ArrayList<Type> type) {
        this.type = type;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
