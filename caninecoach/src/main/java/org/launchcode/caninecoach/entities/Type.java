package org.launchcode.caninecoach.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import org.launchcode.caninecoach.entities.AbstractEntity;
import org.launchcode.caninecoach.entities.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Type extends AbstractEntity {

    private String name;


    public Type(String name) {
        this.name = name;
    }

    @ManyToMany
    @NotNull
    @JoinTable(name = "course_info", joinColumns = { @JoinColumn(name = "type_id")}, inverseJoinColumns = { @JoinColumn(name = "course_id")})
    private ArrayList<Course> courses = new ArrayList<>();

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

    public void setCourses (ArrayList<Course> courses){
        this.courses =courses;
    }

    @ManyToMany(mappedBy = "type")
    private Collection<Course> courses2;

    public Collection<Course> getCourses2() {
        return courses2;
    }

    public void setCourses2(Collection<Course> courses2) {
        this.courses2 = courses2;
    }
}
