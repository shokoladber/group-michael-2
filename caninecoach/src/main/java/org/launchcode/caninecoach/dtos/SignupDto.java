package org.launchcode.caninecoach.dtos;

public class SignupDto {
    private String name;
    private String email;
    private String password; // Changed from char[] for compatibility with JSON processing

    // Default constructor for JSON deserialization
    public SignupDto() {
    }

    public SignupDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
