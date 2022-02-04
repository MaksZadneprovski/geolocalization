package com.example.geolocalization.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@Entity
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 5,max = 30,message = "Длина имени 5-30 символов")
    @Column(name = "us_name")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 5,max = 30,message = "Длина пароля 5-30 символов")
    @Column(name = "pass")
    private String password;

    @Column(name = "us_key")
    private String key;

    private Role role;



    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<CoordinatesEntity> coordinatesEntityList;

    public UserEntity() {}



    public Set<GrantedAuthority> getAuthorities() {
        return new HashSet<>(Arrays.asList(new SimpleGrantedAuthority(getRole().name())));
    }

    public void generateKey() {
        this.key = Base64.getEncoder().encodeToString(getUsername().getBytes());
    }
}
