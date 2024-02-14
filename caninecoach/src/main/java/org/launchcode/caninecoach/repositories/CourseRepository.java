package org.launchcode.caninecoach.repositories;

import jakarta.persistence.Id;
import org.launchcode.caninecoach.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository <Course, Integer> {


    Optional<Course> findById(Id id);
}
