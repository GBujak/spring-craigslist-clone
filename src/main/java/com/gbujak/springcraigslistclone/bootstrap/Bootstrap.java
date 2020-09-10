package com.gbujak.springcraigslistclone.bootstrap;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        if (userDetailsService.loadUserByUsername("admin") != null) {
            System.out.println("--> not running bootstrap, admin exists!");
            return;
        }

        var testUser = new ApplicationUser();
        testUser.setUsername("testuser");
        testUser.setPassword(passwordEncoder.encode("password"));
        System.out.println(userDetailsService.save(testUser));

        var admin = new ApplicationUser();
        admin.setUsername("admin");
        admin.setIsAdmin(true);
        admin.setPassword(passwordEncoder.encode("admin"));
        userDetailsService.save(admin);

        var ad = new Advertisement();
        ad.setHtmlContent("<h1>ad test</h1>");
        ad.setCategory(AdvertisementCategory.COMMUNITY);
        ad.setImages(new ArrayList<>());
        ad.setTitle("test ad");
        adService.save(ad);
    }
}
