package com.example.geolocalization.repository;

import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity,Long> {
    UserEntity findByUsername(String name);
    UserEntity findByPassword(String name);

}
