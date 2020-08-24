package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AbuseReport;
import com.gbujak.springcraigslistclone.service.AbuseReportService;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import com.gbujak.springcraigslistclone.util.AbuseReportFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ModelAttribute(value = "newReport")
    public AbuseReportFormObject abuseReportFormObject() {
        return new AbuseReportFormObject();
    }

    @PostMapping({"", "/"})
    public RedirectView postAbuseReport(@ModelAttribute AbuseReportFormObject newAbuseReport, ModelMap model) {
        var advertisement = advertisementService.findById(newAbuseReport.getAdvertisementId())
                .orElseThrow(() -> new IllegalArgumentException("Advertisement does not exist"));

        var abuseReport = new AbuseReport();

        abuseReport.setDescription(newAbuseReport.getDescription());
        abuseReport.setAdvertisement(advertisement);
        abuseReportService.save(abuseReport);

        return new RedirectView("ogloszenie/" + newAbuseReport.getAdvertisementId());
    }
}
