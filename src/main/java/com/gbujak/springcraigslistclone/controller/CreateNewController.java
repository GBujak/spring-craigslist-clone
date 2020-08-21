package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.Advertisement;
import com.gbujak.springcraigslistclone.model.Image;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserDetailsService;
import com.gbujak.springcraigslistclone.util.CreateNewAdFormObject;
import com.gbujak.springcraigslistclone.util.ImageDiskSaver;
import com.gbujak.springcraigslistclone.util.UserInputProcessor;
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
    private UserInputProcessor inputProcessor;
    private ImageDiskSaver imageDiskSaver;

    public CreateNewController(AdvertisementService adService,
                               UserInputProcessor inputProcessor,
                               ImageDiskSaver imageDiskSaver) {
        this.adService = adService;
        this.inputProcessor = inputProcessor;
        this.imageDiskSaver = imageDiskSaver;
    }

    @ModelAttribute(value = "newAdvertisement")
    public CreateNewAdFormObject newAdvertisement() {
        return new CreateNewAdFormObject();
    }

    @GetMapping({"", "/"})
    public String createNew() {
        return "create-new-advertisement";
    }

    @PostMapping({"", "/"})
    public RedirectView postNew(@ModelAttribute CreateNewAdFormObject newAdForm,
                                Principal principal, Model model) {
        var advertisement = new Advertisement();
        advertisement.setUserName(principal.getName());

        advertisement.setTitle(newAdForm.getTitle());
        advertisement.setCategory(newAdForm.getCategory());

        // Convert markdown to html and sanitize
        advertisement.setHtmlContent(inputProcessor.process(newAdForm.getContent()));

        var imageSet = imageDiskSaver.fromMultipartsSaveToService(newAdForm.getImages());
        advertisement.setImages(imageSet);

        var saved = adService.save(advertisement);
        System.out.println(saved);
        return new RedirectView("ogloszenie/" + saved.getId());
    }
}
