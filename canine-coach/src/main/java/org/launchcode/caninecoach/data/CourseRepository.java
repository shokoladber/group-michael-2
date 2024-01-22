package org.launchcode.caninecoach.data;

import jakarta.transaction.Transactional;
import org.launchcode.caninecoach.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository <Course, Integer> {


}
