package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
}
