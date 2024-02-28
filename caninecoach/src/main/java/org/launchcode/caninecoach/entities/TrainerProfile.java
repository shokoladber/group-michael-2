package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity
public class TrainerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // Link to the User entity

    private String name;
    @Column(length = 1024)
    private String bio;
    private String specialties;

    public TrainerProfile() {
    }

    public TrainerProfile(Long id, User user, String name, String bio, String specialties) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.bio = bio;
        this.specialties = specialties;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }
}
