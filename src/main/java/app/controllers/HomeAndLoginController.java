package app.controllers;

import app.models.UserDetails;
import app.repositories.UserDetailsRepository;
import app.services.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeAndLoginController {

    private UserDetailsService userDetailsService;

    private UserDetailsRepository userDetailsRepository;

    public HomeAndLoginController(UserDetailsService userDetailsService, UserDetailsRepository userDetailsRepository) {
        this.userDetailsService = userDetailsService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping(value = {"/", "/home", "/fooldal"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = {"/login", "/bejelentkezes"})
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = {"/login-error"})
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }

    @GetMapping(value = {"/register"})
    public String saveUserPage(Model model) {
        model.addAttribute("user", new UserDetails());

        return "registration";
    }

    @PostMapping(value = {"/register"})
    public String saveUser(UserDetails userDetails) {
        userDetailsService.saveUser(userDetails);

        return "redirect:/login";
    }

}
