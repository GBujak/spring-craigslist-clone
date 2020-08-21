package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.service.ApplicationUserService;
import com.gbujak.springcraigslistclone.util.RegisterFormObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("rejestracja")
public class RegisterController {
    private ApplicationUserService userService;

    public RegisterController(ApplicationUserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(value = "newUser")
    public RegisterFormObject newUser() {
        return new RegisterFormObject();
    }

    @GetMapping({"", "/"})
    public String getRegister() {
        return "register";
    }

    @PostMapping({"", "/"})
    public ModelAndView postRegister(@ModelAttribute RegisterFormObject registerForm, ModelMap model) {
        var userOptional = userService.findByUsername(registerForm.getUsername());
        if (userOptional.isPresent()) {
            model.addAttribute("formError", "Nazwa użytkownika już istnieje! Użyj innej.");
            return new ModelAndView("register", model);
        }
        if (registerForm.getPasswordIfSame().isEmpty()) {
            model.addAttribute("formError", "Podane hasła są różne!");
            return new ModelAndView("register", model);
        }

        var user = new ApplicationUser();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());

        System.out.println(userService.save(user));

        return new ModelAndView("redirect:/login");
    }
}
