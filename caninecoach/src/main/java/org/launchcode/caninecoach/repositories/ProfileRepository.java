package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {


}
