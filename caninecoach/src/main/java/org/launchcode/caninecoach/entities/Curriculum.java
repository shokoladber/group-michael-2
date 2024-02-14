package org.launchcode.caninecoach.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import org.launchcode.caninecoach.entities.AbstractEntity;
import org.launchcode.caninecoach.entities.Course;


@Entity
public class Curriculum extends AbstractEntity {

    private String week1;

    private String week2;

    private String week3;

    private String week4;

    private String week5;

    private String week6;

    @OneToOne
    @NotNull
    @JoinColumn(name = "course_id")
    private Course course;

    public Curriculum(String week1, String week2, String week3, String week4, String week5, String week6, Course course) {
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.week6 = week6;
        this.course = course;
    }

    public Curriculum(Course course) {
        this.course = course;
    }

    public Curriculum() {
    }

    public String getWeek1() {
        return week1;
    }

    public void setWeek1(String week1) {
        this.week1 = week1;
    }

    public String getWeek2() {
        return week2;
    }

    public void setWeek2(String week2) {
        this.week2 = week2;
    }

    public String getWeek3() {
        return week3;
    }

    public void setWeek3(String week3) {
        this.week3 = week3;
    }

    public String getWeek4() {
        return week4;
    }

    public void setWeek4(String week4) {
        this.week4 = week4;
    }

    public String getWeek5() {
        return week5;
    }

    public void setWeek5(String week5) {
        this.week5 = week5;
    }

    public String getWeek6() {
        return week6;
    }

    public void setWeek6(String week6) {
        this.week6 = week6;
    }

    public Course getCourse() {
        return course;
    }
}

