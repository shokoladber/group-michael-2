package org.launchcode.caninecoach.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String email;

    @NotNull
    private String password;

    public String getUsername() {
        return email;
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = encoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.password);
    }
}