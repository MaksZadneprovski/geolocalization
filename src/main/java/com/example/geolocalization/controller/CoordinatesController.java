package com.example.geolocalization.controller;

import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
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
    public ResponseEntity getCoordinates(@RequestParam String key){
        return ResponseEntity.ok(userRepo.findByKey(key).getCoordinatesEntityList());
    }

    @PostMapping
    public ResponseEntity saveCoordinates(@RequestBody CoordinatesEntity coordinates,
                                          @RequestParam String key){
        try {
            UserEntity user  = userRepo.findByKey(key);
            if (user.getId() != null){
                coordinates.setUserEntity(user);
                coordinates.setTime(new Date());
                coordinatesRepo.save(coordinates);
                return ResponseEntity.ok("Координаты сохранены");
            }
            return  ResponseEntity.ok("Неверный пароль");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
    @DeleteMapping
    public ResponseEntity deleteCoordinates(@RequestParam String key){
        UserEntity user = userRepo.findByKey(key);
        coordinatesRepo.deleteByUserEntity(user);
        return ResponseEntity.ok("Координаты удалены");
    }

}