package org.launchcode.caninecoach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
public class Course extends AbstractEntity{

    @ManyToOne
    private Type type;

    private Details details;

    private Curriculum curriculum;

    public Course(Type type, Details details, Curriculum curriculum) {
        this.type = type;
        this.details = details;
        this.curriculum = curriculum;
    }

    public Course() {

    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
