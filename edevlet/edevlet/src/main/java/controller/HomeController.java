package com.example.edevlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    // Öğretmen bilgisi
    private static final String TEACHER_NAME = "Nargiz Babayeva";
    private static final String TEACHER_PASSWORD = "12345678";

    // Kullanıcı listesi
    private List<Map<String, String>> users = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("Nargiz Babayeva") && password.equals(TEACHER_PASSWORD)) {
            model.addAttribute("teacherName", TEACHER_NAME);
            return "dashboard";
        } else {
            model.addAttribute("error", "Неверные учетные данные");
            return "index";
        }
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String phone, @RequestParam String email, Model model) {
        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("phone", phone);
        user.put("email", email);
        users.add(user);

        model.addAttribute("users", users);
        model.addAttribute("teacherName", TEACHER_NAME);
        return "dashboard";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model) {
        model.addAttribute("teacherName", TEACHER_NAME);
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String processResetPassword(@RequestParam String email, Model model) {
        model.addAttribute("message", "Пароль отправлен на " + email);
        return "resetPassword";
    }
}
