package org.launchcode.caninecoach.repositories;

import org.junit.jupiter.api.Test;
import org.launchcode.caninecoach.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByEmail_thenUserIsFound() {
        // Given
        User user = new User();
        user.setEmail("email@example.com");
        userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        // Then
        assertTrue(foundUser.isPresent(), "User should be found");
        assertEquals(user.getEmail(), foundUser.get().getEmail(), "Emails should match");
    }

    // ... other tests
}