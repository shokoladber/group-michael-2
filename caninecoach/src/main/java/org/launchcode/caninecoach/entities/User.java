package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity @Table(name = "users") public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean usingOAuth2 = false;

    @Column
    private boolean verified = false;

    @Column(name = "profile_created", nullable = false)
    private boolean profileCreated = false;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole role;

    public User() {
    }

    // Standard getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUsingOAuth2() {
        return usingOAuth2;
    }

    public void setUsingOAuth2(Boolean usingOAuth2) {
        this.usingOAuth2 = usingOAuth2;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(boolean profileCreated) {
        this.profileCreated = profileCreated;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}

