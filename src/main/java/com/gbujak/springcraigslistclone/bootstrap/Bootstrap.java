package com.gbujak.springcraigslistclone.bootstrap;

import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.service.ApplicationUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private ApplicationUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public Bootstrap(ApplicationUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        var testUser = new ApplicationUser();
        testUser.setUsername("testuser");
        testUser.setPassword(passwordEncoder.encode("password"));
        System.out.println(userDetailsService.save(testUser));
    }
}
