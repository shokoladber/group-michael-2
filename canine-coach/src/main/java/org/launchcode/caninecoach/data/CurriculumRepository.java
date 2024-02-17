package org.launchcode.caninecoach.data;

import org.launchcode.caninecoach.models.Curriculum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends CrudRepository <Curriculum, Integer> {
}
