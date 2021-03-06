package com.example.geolocalization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeolocalizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocalizationApplication.class, args);
    }

//    spring.datasource.url=jdbc:mysql://b082cc0529355d:96aeb637@us-cdbr-east-05.cleardb.net/heroku_a538a636d8a0ba2?reconnect=true
//    spring.datasource.username=b082cc0529355d
//    spring.datasource.password=96aeb637
//    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
//    spring.jpa.database-platform = org.hibernate.dialect.MySQL8Dialect
//    spring.jpa.generate-ddl=true
//    spring.jpa.hibernate.ddl-auto = update
//    spring.jpa.show-sql=true
//    spring.datasource.hikari.maxLifeTime : 600000
}
