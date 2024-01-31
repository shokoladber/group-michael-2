package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

}
