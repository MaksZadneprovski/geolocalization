package com.example.geolocalization.repository;

import com.example.geolocalization.entity.CoordinatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CoordinatesRepo extends CrudRepository<CoordinatesEntity,Long> {
    List<CoordinatesEntity> findCoordinatesEntitiesByUserId(Long userId);
    List<CoordinatesEntity> findCoordinatesEntitiesByUserIdAndTimeAfterAndTimeBefore(Long userId, Date timeAfter, Date timeBefore);
}
