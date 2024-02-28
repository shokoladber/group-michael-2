package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.PetProfile;
import org.launchcode.caninecoach.repositories.PetProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository) {
        this.petProfileRepository = petProfileRepository;
    }

    public List<PetProfile> findByUserId(Long userId) {
        return petProfileRepository.findByUserId(userId);
    }

    public PetProfile createPetProfile(PetProfile petProfile) {
        return petProfileRepository.save(petProfile);
    }

    // Add more service methods as needed
}
