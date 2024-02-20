package org.launchcode.caninecoach.dtos;

public class TokenResponseDto {

    private String token;
    private boolean isNewUser;

    public TokenResponseDto(String token, boolean isNewUser) {
        this.token = token;
        this.isNewUser = isNewUser;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }
}
