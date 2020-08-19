package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
    @GetMapping
    public String index(Model model) {
        var categories = Arrays.stream(AdvertisementCategory.values())
                .map(AdvertisementCategory::getName)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        return "index";
    }
}
