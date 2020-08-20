package com.gbujak.springcraigslistclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stworz-nowe")
public class CreateNewController {
    @GetMapping({"", "/"})
    public String createNew() {
        return "create-new";
    }
}
