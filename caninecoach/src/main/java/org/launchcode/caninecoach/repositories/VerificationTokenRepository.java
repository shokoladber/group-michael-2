package org.launchcode.caninecoach.repositories;

import org.launchcode.caninecoach.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
