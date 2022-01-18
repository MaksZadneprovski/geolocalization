package com.example.geolocalization.security;

import com.example.geolocalization.entity.Role;
import com.example.geolocalization.entity.UserEntity;
import com.example.geolocalization.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserSecurity.fromUser(user);
    }

    public UserEntity findUserById(Long userId) {
        Optional<UserEntity> userFromDb = userRepo.findById(userId);
        return userFromDb.orElse(new UserEntity());
    }

    public Iterable<UserEntity> allUsers() {
        return userRepo.findAll();
    }

    public boolean saveUser(UserEntity user) {
        UserEntity userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword("{noop}"+user.getPassword());
        user.setRole(Role.USER);
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

}
