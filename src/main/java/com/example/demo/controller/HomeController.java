package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Year;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("anio", Year.now().getValue());
        return "index";  // index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // register.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // dashboard.html
    }
}
