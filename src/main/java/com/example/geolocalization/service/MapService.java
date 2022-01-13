package com.example.geolocalization.service;

import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.repository.CoordinatesRepo;
import com.example.geolocalization.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CoordinatesRepo coordinatesRepo;

    public List<Double> getLat(List<CoordinatesEntity> coordinatesEntityList) {
        List<Double> lat= new ArrayList<>();
         coordinatesEntityList.forEach(x-> lat.add(x.getLatitude()));
         return lat;
    }

    public List<Double> getLng(List<CoordinatesEntity> coordinatesEntityList) {
        List<Double> lng= new ArrayList<>();
        coordinatesEntityList.forEach(x-> lng.add(x.getLongitude()));
        return lng;
    }

    public List<CoordinatesEntity> getCoordinatesEntityList(String name,  String startDate, String endDate) {
        if (name!=null) {
            // Если есть фильтр в запросе, выбираем из БД координаты за определенный период
            // Иначе отображаем за все время
            if(startDate!= null && !startDate.isEmpty() && endDate!= null && !endDate.isEmpty()){

                // Получаем пользователя
                UserEntity user = (UserEntity) userService.loadUserByUsername(name);

                // Получаем ID юзера
                Long userId = user.getId();

                // Парсим дату и время  из переданных строчек GET запросом для фильтрации отображаемого периода
                LocalDateTime timeAfter = LocalDateTime.parse(startDate);
                LocalDateTime timeBefore = LocalDateTime.parse(endDate);

                // LocalDateTime to Date для Spring Data JPA, с другими классами не работает
                Date dateAfter = Date.from(timeAfter.atZone(ZoneId.systemDefault()).toInstant());
                Date dateBefore = Date.from(timeBefore.atZone(ZoneId.systemDefault()).toInstant());

                return new ArrayList<>(coordinatesRepo.findCoordinatesEntitiesByUserIdAndTimeAfterAndTimeBefore(userId,dateAfter,dateBefore));
            }else {
                return new ArrayList<>(userRepo.findByUsername(name).getCoordinatesEntityList());
            }
        }
        return new ArrayList<>();
    }
}
