package org.launchcode.caninecoach.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NotNull
    private String email;

    @NotNull
    private String password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
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

    // Utility methods
    public boolean isMatchingPassword(String password, BCryptPasswordEncoder encoder) {
        return encoder.matches(password, this.password);
    }

}
