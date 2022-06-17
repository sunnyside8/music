package com.example.music;


import com.example.music.model.entity.UserRoleEntity;
import com.example.music.model.entity.enums.UserRoleEnum;
import com.example.music.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {

    private final UserService userService;

    public Init(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        userService.seedUsers();
    }
}
