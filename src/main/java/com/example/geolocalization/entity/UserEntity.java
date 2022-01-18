package com.example.geolocalization.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Role role;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<CoordinatesEntity> coordinatesEntityList;

    public UserEntity() {}

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return new HashSet<>(Arrays.asList(new SimpleGrantedAuthority(getRole().name())));
    }
}
