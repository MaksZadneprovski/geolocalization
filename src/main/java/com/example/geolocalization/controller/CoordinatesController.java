package com.example.geolocalization.controller;

import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.repository.CoordinatesRepo;
import com.example.geolocalization.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesRepo coordinatesRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public ResponseEntity getCoordinates(@RequestParam String name){
        return ResponseEntity.ok(userRepo.findByUsername(name).getCoordinatesEntityList());
    }

    @PostMapping
    public ResponseEntity saveCoordinates(@RequestBody CoordinatesEntity coordinates){
        try {
            coordinates.setTime(new Date());
            coordinatesRepo.save(coordinates);
            return ResponseEntity.ok("Координаты сохранены");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


}