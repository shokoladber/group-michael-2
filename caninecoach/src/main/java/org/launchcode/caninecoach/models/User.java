package org.launchcode.caninecoach.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NotNull
    private String email;

    @NotNull
    private String password;

    // New fields for OAuth2
    private String provider; // store the OAuth2 provider name
    private String providerId; // user's ID from the OAuth2 provider

    // Default constructor
    public User() {}

    // Constructor for traditional registration
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    // Utility method
    public boolean isMatchingPassword(String password, BCryptPasswordEncoder encoder) {
        return encoder.matches(password, this.password);
    }
}
