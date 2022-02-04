package com.example.geolocalization.controller;


import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.repository.CoordinatesRepo;
import com.example.geolocalization.repository.UserRepo;
import com.example.geolocalization.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CoordinatesRepo coordinatesRepo;



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

        return "map2";
    }

    @PostMapping ("/delete")
    public String deleteMap(Principal principal){
        UserEntity user = userRepo.findByUsername(principal.getName());
        coordinatesRepo.deleteByUserEntity(user);
        return "redirect:/map";
    }

}
