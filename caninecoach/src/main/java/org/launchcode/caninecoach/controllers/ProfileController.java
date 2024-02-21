package org.launchcode.caninecoach.controllers;

import jakarta.validation.Valid;
import org.launchcode.caninecoach.entities.Profile;
import org.launchcode.caninecoach.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileRepository profileRepository;
    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/")
    public String displayProfileForm(){
        return "/";
    }

    @PostMapping("/")
    public String addProfile (@Valid Profile newProfile, Errors errors) {
        if (errors.hasErrors()){
            return "/post";
        }
        profileRepository.save(newProfile);
        return "redirect:";
    }

    public String findProfileById(int id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return profile.toString();
        } else {
            return "Profile with ID " + id + " does not exist";
        }
    }

}
