package com.example.geolocalization.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private UserEntity userEntity;

    private Double longitude;
    private Double latitude;

    private Date time;

    public   CoordinatesEntity() {}


}