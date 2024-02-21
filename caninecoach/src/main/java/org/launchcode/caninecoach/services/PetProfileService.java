package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.dtos.PetProfileDto;
import org.launchcode.caninecoach.entities.PetProfile;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.handlers.AppException;
import org.launchcode.caninecoach.repositories.PetProfileRepository;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository, UserRepository userRepository) {
        this.petProfileRepository = petProfileRepository;
        this.userRepository = userRepository;
    }


        public PetProfileDto createPetProfile(String email, PetProfileDto petProfileDto) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));


            if (user.getRole() != UserRole.PET_GUARDIAN) {
                throw new AppException("User is not authorized as PET_GUARDIAN", HttpStatus.FORBIDDEN);
            }


            PetProfile petProfile = new PetProfile();
            petProfile.setName(petProfileDto.getName());
            petProfile.setType(petProfileDto.getType());
            petProfile.setAge(petProfileDto.getAge());
            petProfile.setDescription(petProfileDto.getDescription());
            petProfile.setGuardian(user);


            PetProfile savedPetProfile = petProfileRepository.save(petProfile);


            return convertToDto(savedPetProfile, user.getName());
        }

    public PetProfileDto updatePetProfile(Long id, String username, PetProfileDto petProfileDto) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (user.getRole() != UserRole.PET_GUARDIAN) {
            throw new AppException("User is not authorized as PET_GUARDIAN", HttpStatus.FORBIDDEN);
        }

        PetProfile petProfile = petProfileRepository.findById(id)
                .orElseThrow(() -> new AppException("Pet profile not found", HttpStatus.NOT_FOUND));

        if (!petProfile.getGuardian().equals(user)) {
            throw new AppException("User is not authorized to update this pet profile", HttpStatus.FORBIDDEN);
        }

        petProfile.setName(petProfileDto.getName());
        petProfile.setType(petProfileDto.getType());
        petProfile.setAge(petProfileDto.getAge());
        petProfile.setDescription(petProfileDto.getDescription());

        PetProfile updatedPetProfile = petProfileRepository.save(petProfile);

        return convertToDto(updatedPetProfile, user.getName());
    }


    private PetProfileDto convertToDto(PetProfile petProfile, String ownerName) {
        PetProfileDto dto = new PetProfileDto();
        dto.setId(petProfile.getId());
        dto.setName(petProfile.getName());
        dto.setType(petProfile.getType());
        dto.setAge(petProfile.getAge());
        dto.setDescription(petProfile.getDescription());
        dto.setOwnerName(ownerName);
        return dto;
    }
}
