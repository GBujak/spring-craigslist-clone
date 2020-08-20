package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.AdvertisementCategory;
import com.gbujak.springcraigslistclone.service.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("category")
public class CategoryController {
    private final AdvertisementService adService;

    public CategoryController(AdvertisementService asService) {
        this.adService = asService;
    }

    @GetMapping("{categoryName}")
    public String getListings(@PathVariable String categoryName, Model model) {
        var category = Arrays.stream(AdvertisementCategory.values())
                .filter(it -> it.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("category does not exist"));
        model.addAttribute("categoryName", category.getHumanReadableName());
        model.addAttribute("ads", adService.findByCategory(category));
        return "category";
    }
}
