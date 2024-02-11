package org.launchcode.caninecoach.data;

import org.launchcode.caninecoach.models.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> {
}
