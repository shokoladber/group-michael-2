package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Curriculum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends CrudRepository <Curriculum, Integer> {
}
