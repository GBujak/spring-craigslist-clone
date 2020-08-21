package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.repository.ApplicationUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService {
    private ApplicationUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ApplicationUserService(ApplicationUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUser save(ApplicationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public Optional<ApplicationUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
