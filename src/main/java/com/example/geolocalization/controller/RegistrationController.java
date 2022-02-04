package com.example.geolocalization.controller;

import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.security.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImp userService;

    @GetMapping
    public String registration(@ModelAttribute("user")  UserEntity user,Model model) {
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid UserEntity user,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("########################################################");
            return "registration";
        }
        user.generateKey();
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/map";
    }
}
