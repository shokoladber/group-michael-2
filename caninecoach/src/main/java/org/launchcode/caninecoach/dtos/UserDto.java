package org.launchcode.caninecoach.dtos;

import org.launchcode.caninecoach.entities.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDto implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private String password;
    private String token; // Optional token field, useful for JWT handling but not part of UserDetails

    // Default constructor
    public UserDto() {
    }

    // Constructor with fields, excluding token for UserDetails compatibility
    public UserDto(Long id, String name, String email, UserRole role, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    // Constructor with all fields, including token
    public UserDto(Long id, String name, String email, UserRole role, String password, String token) {
        this(id, name, email, role, password); // Call the main constructor
        this.token = token;
    }

    // UserDetails implementation methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This grants authority based on the UserRole enum. Adjust as necessary for your application's roles.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // The following methods are defaulting to true for simplicity.
    // Implement more complex logic if your application requires it.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        // Note: Be cautious with logging sensitive information like passwords or tokens.
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                // Optionally comment out the next line in production for security
                ", token='" + token + '\'' +
                '}';
    }
}
