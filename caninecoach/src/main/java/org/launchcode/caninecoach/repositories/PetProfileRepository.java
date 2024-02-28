package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {


    List<PetProfile> findByUserId(Long userId);
}
