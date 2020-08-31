package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("kategoria")
public class CategoryController {
    private static final int PAGE_SIZE = 50;

    private final AdvertisementService adService;

    public CategoryController(AdvertisementService asService) {
        this.adService = asService;
    }

    @GetMapping("{categoryName}")
    public String getListings(@PathVariable String categoryName, Model model, @RequestParam(required = false) Integer page) {
        var category = Arrays.stream(AdvertisementCategory.values())
                .filter(it -> it.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("category does not exist"));
        model.addAttribute("categoryName", category.getHumanReadableName());
        model.addAttribute("categoryUrl", categoryName);
        if (page == null) page = 0;
        var currentPage = adService.findByCategory(category, page, PAGE_SIZE);
        model.addAttribute("totalPages", currentPage.getTotalPages());
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("ads", currentPage.getContent());
        return "category";
    }
}
