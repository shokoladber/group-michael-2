package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.PetProfile;
import org.launchcode.caninecoach.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet-profile")
public class PetProfileController {

    private final PetProfileService petProfileService;

    @Autowired
    public PetProfileController(PetProfileService petProfileService) {
        this.petProfileService = petProfileService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PetProfile>> getPetProfilesByUserId(@PathVariable Long userId) {
        List<PetProfile> petProfiles = petProfileService.findByUserId(userId);
        return ResponseEntity.ok(petProfiles);
    }

    @PostMapping("/create")
    public ResponseEntity<PetProfile> createPetProfile(@RequestBody PetProfile petProfile) {
        PetProfile createdPetProfile = petProfileService.createPetProfile(petProfile);
        return ResponseEntity.ok(createdPetProfile);
    }


}
