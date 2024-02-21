package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.dtos.TrainerProfileDto;
import org.launchcode.caninecoach.entities.TrainerProfile;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.repositories.TrainerProfileRepository;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.launchcode.caninecoach.handlers.AppException;

@Service
public class TrainerProfileService {

    private final TrainerProfileRepository trainerProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrainerProfileService(TrainerProfileRepository trainerProfileRepository,
                                 UserRepository userRepository) {
        this.trainerProfileRepository = trainerProfileRepository;
        this.userRepository = userRepository;
    }

    public TrainerProfileDto getTrainerProfile(String username) {
        TrainerProfile trainerProfile = findTrainerProfileByUsername(username);
        return convertToDto(trainerProfile);
    }

    public TrainerProfileDto updateTrainerProfile(String username, TrainerProfileDto dto) {
        TrainerProfile trainerProfile = findTrainerProfileByUsername(username);


        trainerProfile.setSpecialties(dto.getSpecialties());
        trainerProfile.setDescription(dto.getDescription());

        TrainerProfile updatedTrainerProfile = trainerProfileRepository.save(trainerProfile);
        return convertToDto(updatedTrainerProfile);
    }

    private TrainerProfile findTrainerProfileByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (!user.getRole().equals(UserRole.PET_TRAINER)) {
            throw new AppException("User is not authorized as PET_TRAINER", HttpStatus.FORBIDDEN);
        }

        return trainerProfileRepository.findById(user.getId())
                .orElseThrow(() -> new AppException("Trainer profile not found", HttpStatus.NOT_FOUND));
    }

    private TrainerProfileDto convertToDto(TrainerProfile trainerProfile) {
        TrainerProfileDto dto = new TrainerProfileDto();
        dto.setId(trainerProfile.getId());
        dto.setName(trainerProfile.getUser().getName()); // Name is managed at the User level
        dto.setSpecialties(trainerProfile.getSpecialties());
        dto.setDescription(trainerProfile.getDescription());
        return dto;
    }
}
