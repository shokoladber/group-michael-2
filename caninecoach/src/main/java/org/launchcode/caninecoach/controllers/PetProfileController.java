package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.dtos.PetProfileDto;
import org.launchcode.caninecoach.handlers.AppException;
import org.launchcode.caninecoach.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PetProfileController {

    private final PetProfileService petProfileService;

    @Autowired
    public PetProfileController(PetProfileService petProfileService) {
        this.petProfileService = petProfileService;
    }

    @PostMapping("/pet-profiles")
    public ResponseEntity<?> createPetProfile(@RequestBody PetProfileDto petProfileDto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            PetProfileDto newPetProfileDto = petProfileService.createPetProfile(userDetails.getUsername(), petProfileDto);
            return new ResponseEntity<>(newPetProfileDto, HttpStatus.CREATED);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }

    @PutMapping("/pet-profiles/{id}")
    public ResponseEntity<?> updatePetProfile(@PathVariable Long id,
                                              @RequestBody PetProfileDto petProfileDto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        try {
            PetProfileDto updatedPetProfileDto = petProfileService.updatePetProfile(id, userDetails.getUsername(), petProfileDto);
            return new ResponseEntity<>(updatedPetProfileDto, HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        }
    }
}