package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.TrainerProfile;
import org.launchcode.caninecoach.repositories.TrainerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerProfileService {

    private final TrainerProfileRepository trainerProfileRepository;

    @Autowired
    public TrainerProfileService(TrainerProfileRepository trainerProfileRepository) {
        this.trainerProfileRepository = trainerProfileRepository;
    }

    public List<TrainerProfile> findByUserId(Long userId) {
        return trainerProfileRepository.findByUserId(userId);
    }

    public TrainerProfile createTrainerProfile(TrainerProfile trainerProfile) {
        return trainerProfileRepository.save(trainerProfile);
    }


}
