package com.gbujak.springcraigslistclone.service;

import com.gbujak.springcraigslistclone.model.ApplicationUserDetails;
import com.gbujak.springcraigslistclone.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsService implements UserDetailsService {
    private ApplicationUserRepository userRepository;

    public ApplicationUserDetailsService(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new ApplicationUserDetails(user);
    }
}
