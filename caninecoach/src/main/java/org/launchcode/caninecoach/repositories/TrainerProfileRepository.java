package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.PetProfile;
import org.launchcode.caninecoach.entities.TrainerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerProfileRepository extends JpaRepository<TrainerProfile, Long> {

    List<TrainerProfile> findByUserId(Long userId);
}
