package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {
}
