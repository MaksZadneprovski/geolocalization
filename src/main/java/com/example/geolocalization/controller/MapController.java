package com.example.geolocalization.controller;


import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.repository.UserRepo;
import com.example.geolocalization.service.MapService;
import com.example.geolocalization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;



    @GetMapping
    public String getMap(@RequestParam(required = false, defaultValue = "") String startDate,
                         @RequestParam(required = false, defaultValue = "") String endDate,
                         Model model,
                         Principal principal){

        // Определяем имя пользователя
        String userName = principal.getName();

        // Создаем список координат, учитывая имя пользователя и фильтр
        List<CoordinatesEntity> coordinatesEntityList = mapService.getCoordinatesEntityList(userName, startDate,endDate);

        // Добавляем список координат в модель для отображения координат на странице
        model.addAttribute("coordinates",coordinatesEntityList);

        // Добавляем списки  широт и долгот в модель для  JS скрипта, чтобы тот строил маршруты на карте
        model.addAttribute("lat",mapService.getLat(coordinatesEntityList));
        model.addAttribute("lng",mapService.getLng(coordinatesEntityList));

        return "/map2";
    }

    @PostMapping
    public String getRoute(){

        return "map";
    }

}
