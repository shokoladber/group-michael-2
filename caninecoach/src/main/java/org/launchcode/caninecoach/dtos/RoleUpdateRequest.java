package org.launchcode.caninecoach.dtos;

import jakarta.validation.constraints.NotBlank;

public class RoleUpdateRequest {
    @NotBlank(message = "New role cannot be blank")
    private String newRole;

    // Getters and setters
    public String getNewRole() {
        return newRole;
    }

    public void setNewRole(String newRole) {
        this.newRole = newRole;
    }
}
