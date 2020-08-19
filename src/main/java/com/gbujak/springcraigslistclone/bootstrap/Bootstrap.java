package com.gbujak.springcraigslistclone.bootstrap;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class Bootstrap implements CommandLineRunner {
    private final ApplicationUserDetailsService userDetailsService;
    private final AdvertisementService adService;
    private final PasswordEncoder passwordEncoder;

    public Bootstrap(ApplicationUserDetailsService userDetailsService,
                     AdvertisementService adService,
                     PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.adService = adService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        var testUser = new ApplicationUser();
        testUser.setUsername("testuser");
        testUser.setPassword(passwordEncoder.encode("password"));
        System.out.println(userDetailsService.save(testUser));

        var ad = new Advertisement();
        ad.setAdvertisementSafeHtmlContent("<h1>ad test</h1>");
        ad.setCategory(AdvertisementCategory.COMMUNITY);
        ad.setImages(new HashSet<>());
        ad.setTitle("test ad");
        adService.save(ad);
    }
}
