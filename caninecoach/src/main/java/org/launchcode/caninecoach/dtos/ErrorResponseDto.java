package org.launchcode.caninecoach.dtos;

public class ErrorResponseDto {
    private String message;

    // Default constructor for JSON deserialization
    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
