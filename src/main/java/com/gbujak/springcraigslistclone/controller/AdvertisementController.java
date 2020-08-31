package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("ogloszenie")
public class AdvertisementController {
    private final AdvertisementService adService;
    private final ApplicationUserService userService;

    public AdvertisementController(AdvertisementService adService, ApplicationUserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    @GetMapping("{adId}")
    public String getAd(@PathVariable Long adId,
                        @RequestParam(required = false) String message,
                        Model model,
                        Principal principal) {

        var ad = adService.findById(adId).orElseThrow(
                () -> new IllegalArgumentException("Ad not found"));

        var userOptional = userService.findByUsername(principal.getName());
        model.addAttribute("canDelete", userOptional.isPresent()
                && (userOptional.get().getIsAdmin()
                || principal.getName().equals(ad.getUserName())));

        model.addAttribute("ad", ad);
        model.addAttribute("message", message);
        return "advertisement";
    }
}
