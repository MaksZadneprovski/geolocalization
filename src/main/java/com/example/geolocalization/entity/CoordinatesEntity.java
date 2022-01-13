package com.example.geolocalization.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn
    private Long userId;
    private Double longitude;
    private Double latitude;

    private Date time;

    protected  CoordinatesEntity() {}


}