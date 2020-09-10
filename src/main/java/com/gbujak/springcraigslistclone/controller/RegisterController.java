package com.gbujak.springcraigslistclone.controller;

import com.gbujak.springcraigslistclone.model.ApplicationUser;
import com.gbujak.springcraigslistclone.service.ApplicationUserService;
import com.gbujak.springcraigslistclone.util.RegisterFormObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("rejestracja")
public class RegisterController {
    private ApplicationUserService userService;
    private PasswordEncoder passwordEncoder;

    public RegisterController(ApplicationUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping({"zmiana-hasla"})
    public ModelAndView getChangePassword(Principal principal) {
        if (principal == null) return new ModelAndView("redirect:/login");
        return new ModelAndView("change-password");
    }

    @PostMapping({"zmiana-hasla"})
    public ModelAndView postChangePassword(
            ModelMap model,
            Principal principal,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("newPasswordRepeat") String newPasswordRepeat) {

        if (principal == null) return new ModelAndView("redirect:/login");
        var userOptional = userService.findByUsername(principal.getName());

        if (userOptional.isEmpty()) return new ModelAndView("redirect:/login");
        var user = userOptional.get();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            model.addAttribute("formError", "Stare hasło podane niepoprawnie");
            return new ModelAndView("change-password", model);
        }

        if (!newPassword.equals(newPasswordRepeat)) {
            model.addAttribute("formError", "Hasła nie są identyczne!");
            return new ModelAndView("change-password", model);
        }

        user.setPassword(newPassword);
        userService.save(user);

        return new ModelAndView("redirect:/");
    }
}
