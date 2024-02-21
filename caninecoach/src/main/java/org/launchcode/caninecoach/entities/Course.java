package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;


@Entity
public class Course extends AbstractEntity {



    @OneToOne(mappedBy = "course")
    private CourseInfo courseInfo;


    @OneToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    private String name;

    public Course(CourseInfo courseInfo, Curriculum curriculum, String name) {
        this.courseInfo = courseInfo;
        this.curriculum = curriculum;
        this.name = name;

    }

    public Course() {

    }

    public ArrayList<Type> getType() {
        return type;
    }

    public void setType(ArrayList<Type> type) {
        this.type = type;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getName() {
        return name;
    }
}
