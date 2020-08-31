package com.gbujak.springcraigslistclone.repository;

import com.gbujak.springcraigslistclone.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
    void deleteByUsername(String username);
}
