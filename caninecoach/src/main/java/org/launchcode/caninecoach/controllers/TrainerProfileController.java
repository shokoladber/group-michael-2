package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.dtos.TrainerProfileDto;
import org.launchcode.caninecoach.services.TrainerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer-profiles")
public class TrainerProfileController {

    private final TrainerProfileService trainerProfileService;

    @Autowired
    public TrainerProfileController(TrainerProfileService trainerProfileService) {
        this.trainerProfileService = trainerProfileService;
    }

    @GetMapping
    public ResponseEntity<TrainerProfileDto> getTrainerProfile(@AuthenticationPrincipal UserDetails userDetails) {
        TrainerProfileDto profile = trainerProfileService.getTrainerProfile(userDetails.getUsername());
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<TrainerProfileDto> updateTrainerProfile(@AuthenticationPrincipal UserDetails userDetails,
                                                                  @RequestBody TrainerProfileDto trainerProfileDto) {
        TrainerProfileDto updatedProfile = trainerProfileService.updateTrainerProfile(userDetails.getUsername(), trainerProfileDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
