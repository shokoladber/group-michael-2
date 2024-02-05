package org.launchcode.caninecoach.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.launchcode.caninecoach.models.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}
