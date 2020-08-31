package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.service.AbuseReportService;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {
    private static final int PAGE_SIZE = 50;

    private final AbuseReportService abuseReportService;
    private final AdvertisementService advertisementService;

    public AdminController(AbuseReportService abuseReportService, AdvertisementService advertisementService) {
        this.abuseReportService = abuseReportService;
        this.advertisementService = advertisementService;
    }

    @GetMapping("zgloszenia")
    public ModelAndView getReports(ModelMap model, @RequestParam(required = false) Integer page) {
        if (page == null) page = 0;
        var reportPage = abuseReportService.findPaginated(page, PAGE_SIZE);
        model.addAttribute("totalPages", reportPage.getTotalPages());
        model.addAttribute("reports", reportPage.getContent());
        model.addAttribute("nextPage", page + 1);
        return new ModelAndView("admin-view-reports", model);
    }

    @GetMapping("usun-ogloszenie/{id}")
    public ModelAndView deleteAdvertisement(ModelMap model, @PathVariable Long id) {
        var advert = advertisementService.findById(id);
        model.addAttribute("message", (advert.isPresent()) ? "Pomyślnie usunięto ogłoszenie"
                                                                          : "Nie znaleziono ogłoszenia");
        advert.ifPresent(advertisementService::delete);

        return new ModelAndView("admin-view-reports", model);
    }
}
