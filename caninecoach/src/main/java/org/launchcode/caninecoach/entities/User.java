package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

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
    @Column(name = "role", nullable = false)
    private UserRole role;

    // No-args constructor
    public User() {
    }

    // All-args constructor
    public User(Long id, String email, String name, String password, Boolean usingOAuth2, boolean verified, boolean profileCreated, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.usingOAuth2 = usingOAuth2;
        this.verified = verified;
        this.profileCreated = profileCreated;
        this.role = role;
    }

    // Getters and Setters
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

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", usingOAuth2=" + usingOAuth2 +
                ", verified=" + verified +
                ", profileCreated=" + profileCreated +
                ", role=" + role +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (verified != user.verified) return false;
        if (profileCreated != user.profileCreated) return false;
        if (!id.equals(user.id)) return false;
        if (!email.equals(user.email)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        if (!usingOAuth2.equals(user.usingOAuth2)) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + usingOAuth2.hashCode();
        result = 31 * result + (verified ? 1 : 0);
        result = 31 * result + (profileCreated ? 1 : 0);
        result = 31 * result + role.hashCode();
        return result;
    }
}
