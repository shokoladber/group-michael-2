package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity
public class PetProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String guardianName;
    private String petName;
    private Integer petAge;
    private String petBreed;
    @Column(length = 1024)
    private String petBio;

    public PetProfile() {
    }

    public PetProfile(Long id, User user, String guardianName, String petName, Integer petAge, String petBreed, String petBio) {
        this.id = id;
        this.user = user;
        this.guardianName = guardianName;
        this.petName = petName;
        this.petAge = petAge;
        this.petBreed = petBreed;
        this.petBio = petBio;
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

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetBio() {
        return petBio;
    }

    public void setPetBio(String petBio) {
        this.petBio = petBio;
    }
}

