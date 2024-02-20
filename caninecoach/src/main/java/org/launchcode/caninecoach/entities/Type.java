package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.launchcode.caninecoach.entities.AbstractEntity;
import org.launchcode.caninecoach.entities.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


}
