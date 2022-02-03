package com.example.geolocalization.controller;

import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String getSettings(Model model, Principal principal){
        UserEntity user = userRepo.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "settings";
    }
}
