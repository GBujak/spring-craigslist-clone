package com.gbujak.springcraigslistclone.controller;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {
    @GetMapping("{category}")
    public String getListings(@PathVariable String category,
                              Model model) throws NotFoundException {
        model.addAttribute("test", "category test");
        return "category";
    }
}
