package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue
    private long favorite_id;

    @ManyToOne
    @JoinColumn(name= "course_id")
    private Course course;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> user;


    public Favorite(long favorite_id, Course course, List<User> user) {
        this.favorite_id = favorite_id;
        this.course = course;
        this.user = user;
    }

    public Favorite() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public long getFavorite_id() {
        return favorite_id;
    }
}
