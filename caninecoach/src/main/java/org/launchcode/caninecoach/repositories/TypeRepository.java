package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Course;
import org.launchcode.caninecoach.entities.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {

    Optional<Course> findByType(String type);
}
