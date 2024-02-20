package org.launchcode.caninecoach.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.launchcode.caninecoach.entities.AbstractEntity;
import org.launchcode.caninecoach.entities.Course;

@Entity
public class Details extends AbstractEntity {
    @NotBlank
    private String length;
    @NotBlank
    private String description;


    private Integer price;

    @OneToOne(mappedBy = "details")
    private CourseInfo courseInfo;;

    public Details() {
    }


    public Details(String length, String description, Integer price, CourseInfo courseInfo) {
        this.length = length;
        this.description = description;
        this.price = price;
        this.courseInfo = courseInfo;
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

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }
}
