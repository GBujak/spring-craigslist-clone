package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", AdvertisementCategory.values());
        return "index";
    }
}
