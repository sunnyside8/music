package com.example.music.service.impl;

import com.example.music.model.entity.UserEntity;
import com.example.music.model.entity.UserRoleEntity;
import com.example.music.model.entity.enums.UserRoleEnum;
import com.example.music.repository.UserRepository;
import com.example.music.repository.UserRoleRepository;
import com.example.music.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {
        if(userRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);

            UserEntity userEntity = new UserEntity();
            userEntity.setName("admin");
            userEntity.setPassword(passwordEncoder.encode("12345"));
            userEntity.setRoles(Set.of(adminRole,userRole));
            userEntity.setActive(false);

            userRepository.save(userEntity);

            UserEntity user = new UserEntity();
            user.setName("user");
            user.setPassword(passwordEncoder.encode("12345"));
            user.setRoles(Set.of(userRole));
            user.setActive(true);

            userRepository.save(user);

        }
    }
}
