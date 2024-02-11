package org.launchcode.caninecoach.data;

import org.launchcode.caninecoach.models.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
}
