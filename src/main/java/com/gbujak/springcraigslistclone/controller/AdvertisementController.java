package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("ogloszenie")
public class AdvertisementController {
    private final AdvertisementService adService;

    public AdvertisementController(AdvertisementService adService) {
        this.adService = adService;
    }

    @GetMapping("{adId}")
    public String getAd(@PathVariable Long adId, @RequestParam(required = false) String message, Model model) {
        var ad = adService.findById(adId).orElseThrow(
                () -> new IllegalArgumentException("Ad not found"));
        model.addAttribute("ad", ad);
        model.addAttribute("message", message);
        return "advertisement";
    }
}
