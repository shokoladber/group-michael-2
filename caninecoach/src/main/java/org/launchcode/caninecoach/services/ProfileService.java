package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.Profile;
import org.launchcode.caninecoach.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void addProfile(Profile newProfile) {
        profileRepository.save(newProfile);
    }

    public Optional<Profile> findProfileById(int id) {
        return profileRepository.findById(id);
    }

}
