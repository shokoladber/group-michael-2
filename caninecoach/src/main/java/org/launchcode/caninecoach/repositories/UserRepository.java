package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.models.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

