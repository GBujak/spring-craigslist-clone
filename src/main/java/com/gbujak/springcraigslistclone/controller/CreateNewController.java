package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("stworz-nowe")
public class CreateNewController {
    private AdvertisementService adService;

    public CreateNewController(AdvertisementService adService) {
        this.adService = adService;
    }

    @ModelAttribute(value = "newAdvertisement")
    public Advertisement newAdvertisement() {
        return new Advertisement();
    }

    @GetMapping({"", "/"})
    public String createNew() {
        return "create-new-advertisement";
    }

    @PostMapping({"", "/"})
    public RedirectView postNew(@ModelAttribute Advertisement advertisement,
                                Principal principal,
                                Model model) {
        advertisement.setUserName(principal.getName());
        var saved = adService.save(advertisement);
        System.out.println(saved);
        return new RedirectView("ogloszenie/" + saved.getId());
    }
}
