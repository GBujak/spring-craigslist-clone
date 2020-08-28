package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AbuseReport;
import com.gbujak.springcraigslistclone.service.AbuseReportService;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.util.AbuseReportFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("zglos-naduzycie")
public class AbuseReportController {
    private AbuseReportService abuseReportService;
    private AdvertisementService advertisementService;

    public AbuseReportController(AbuseReportService abuseReportService, AdvertisementService advertisementService) {
        this.abuseReportService = abuseReportService;
        this.advertisementService = advertisementService;
    }

    @PostMapping({"", "/"})
    public ModelAndView postAbuseReport(
            ModelMap model,
            @RequestParam("advertisementId") Long advertisementId,
            @RequestParam("description") String description
    ) {
        var advertisement = advertisementService.findById(advertisementId)
                .orElseThrow(() -> new IllegalArgumentException("Advertisement does not exist"));

        var abuseReport = new AbuseReport();

        abuseReport.setDescription(description);
        abuseReport.setAdvertisement(advertisement);
        abuseReportService.save(abuseReport);

        model.addAttribute("message", "Zgłoszono nadużycie!");

        return new ModelAndView("redirect:/ogloszenie/" + advertisementId, model);
    }
}
