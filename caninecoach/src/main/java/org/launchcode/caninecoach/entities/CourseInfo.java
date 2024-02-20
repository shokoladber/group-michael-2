package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "course_info")
public class CourseInfo extends AbstractEntity{


        @OneToOne
        @JoinColumn(name = "course_id")
        private Course course;

        @ManyToOne
        @JoinColumn(name = "type_id")
        private Type type;

        @OneToOne
        @JoinColumn(name = "details_id")
        private Details details;

    public CourseInfo(Course course, Type type, Details details) {
        this.course = course;
        this.type = type;
        this.details = details;
    }

    public CourseInfo() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
}


