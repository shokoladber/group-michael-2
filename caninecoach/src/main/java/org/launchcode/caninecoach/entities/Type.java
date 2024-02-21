package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;
import java.util.Set;
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

    @OneToMany(mappedBy = "type")
    private Set<CourseInfo> courseInfos;

    public Type(String name, Set<CourseInfo> courseInfos) {
        this.name = name;
        this.courseInfos = courseInfos;
    }

    public Type() {
    }

    public Set<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(Set<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
