package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.TrainerProfile;
import org.launchcode.caninecoach.services.TrainerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer-profile")
public class TrainerProfileController {

    private final TrainerProfileService trainerProfileService;

    @Autowired
    public TrainerProfileController(TrainerProfileService trainerProfileService) {
        this.trainerProfileService = trainerProfileService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainerProfile>> getTrainerProfilesByUserId(@PathVariable Long userId) {
        List<TrainerProfile> trainerProfiles = trainerProfileService.findByUserId(userId);
        return ResponseEntity.ok(trainerProfiles);
    }

    @PostMapping("/create")
    public ResponseEntity<TrainerProfile> createTrainerProfile(@RequestBody TrainerProfile trainerProfile) {
        TrainerProfile createdTrainerProfile = trainerProfileService.createTrainerProfile(trainerProfile);
        return ResponseEntity.ok(createdTrainerProfile);
    }


}
