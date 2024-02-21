package org.launchcode.caninecoach.entities;

import jakarta.persistence.*;

import java.util.Objects;

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


    public User() {
    }

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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                ", usingOAuth2=" + usingOAuth2 +
                ", verified=" + verified +
                ", profileCreated=" + profileCreated +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return verified == user.verified &&
                profileCreated == user.profileCreated &&
                id.equals(user.id) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(usingOAuth2, user.usingOAuth2) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, usingOAuth2, verified, profileCreated, role);
    }
}
