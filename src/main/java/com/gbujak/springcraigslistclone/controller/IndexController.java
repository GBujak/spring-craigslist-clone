package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
    private AdvertisementService adService;

    public IndexController(AdvertisementService adService) {
        this.adService = adService;
    }

    @GetMapping
    public String index(Model model) {

        var categoryToCount = new TreeMap<AdvertisementCategory, Long>();
        Arrays.stream(AdvertisementCategory.values())
                .forEach(it -> categoryToCount.put(it, adService.countByCategory(it)));

        model.addAttribute("categoryToCount", categoryToCount);
        return "index";
    }
}
