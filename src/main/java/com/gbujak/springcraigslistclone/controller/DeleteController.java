package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.service.ApplicationUserService;
import com.gbujak.springcraigslistclone.util.ImageDiskSaver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.security.Principal;

@Controller
@RequestMapping("usun")
public class DeleteController {

    private ApplicationUserService userService;
    private AdvertisementService adService;
    private ImageDiskSaver imageDiskSaver;

    public DeleteController(ApplicationUserService userService, AdvertisementService adService, ImageDiskSaver imageDiskSaver) {
        this.userService = userService;
        this.adService = adService;
        this.imageDiskSaver = imageDiskSaver;
    }

    @GetMapping("{id}")
    public RedirectView deleteAdvert(@PathVariable Long id, Principal principal) {
        var ad = adService.findById(id);
        var userOptional = userService.findByUsername(principal.getName());
        if (
                userOptional.isPresent()
                && ad.isPresent()
                && (userOptional.get().getIsAdmin()
                || principal.getName().equals(ad.get().getUserName()))
        ) {
            ad.get().getImages().forEach(
                    it -> imageDiskSaver.delete(it.getFileUrl()));
            adService.delete(ad.get());
        }

        return new RedirectView("/");
    }

    @Transactional
    @GetMapping("konto")
    public RedirectView deleteAccount(Principal principal) {
        if (principal != null) {
            var ads = adService.findByUserName(principal.getName());
            ads.forEach(adService::delete);
            userService.delete(principal.getName());
            System.out.println("Deleted user: " + principal.getName());
        }
        return new RedirectView("/");
    }
}
