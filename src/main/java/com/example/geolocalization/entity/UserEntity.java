package com.example.geolocalization.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_name")
    private String username;

    @Column(name = "pass")
    private String password;

    @Column(name = "us_key")
    private String key;

    private Role role;



    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<CoordinatesEntity> coordinatesEntityList;

    public UserEntity() {}

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.key = Base64.getEncoder().encodeToString(password.getBytes());
    }

    public Set<GrantedAuthority> getAuthorities() {
        return new HashSet<>(Arrays.asList(new SimpleGrantedAuthority(getRole().name())));
    }
}
