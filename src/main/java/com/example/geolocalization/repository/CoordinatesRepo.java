package com.example.geolocalization.repository;

import com.example.geolocalization.entity.CoordinatesEntity;
import com.example.geolocalization.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CoordinatesRepo extends CrudRepository<CoordinatesEntity,Long> {
    List<CoordinatesEntity> findCoordinatesEntitiesByUserEntityId(Long userId);
    List<CoordinatesEntity> findCoordinatesEntitiesByUserEntityIdAndTimeAfterAndTimeBefore(Long userId, Date timeAfter, Date timeBefore);
    void deleteByUserEntity(UserEntity user);
}
