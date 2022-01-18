package com.example.geolocalization.controller;

import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.security.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImp userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "registration";
    }

    @PostMapping
    public String addUser(@RequestParam("username")  String username,
                          @RequestParam("password")  String password, Model model) {

        if (!userService.saveUser(new UserEntity(username,password))){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/map";
    }
}
