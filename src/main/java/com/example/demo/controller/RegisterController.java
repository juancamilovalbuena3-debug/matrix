package com.example.demo.controller;

import com.example.hr.model.User;
import com.example.hr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // <-- Inyectamos encoder

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam("password_confirmation") String confirmPassword,
            Model model
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "register";
        }

        // Crear objeto usuario
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // <-- encriptada

        // Guardarlo en MySQL
        userRepository.save(user);

        return "redirect:/?success";
    }
}
