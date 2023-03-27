package com.team3dat3.backend.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.service.UserService;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Developer config
 */

@Configuration
public class DeveloperConfig implements ApplicationRunner {

    private UserService userService;

    public DeveloperConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.create(new UserRequest(new User("admin", "admin", "mail1@eg.com", "12345678", new String[] {"ADMIN", "MEMBER"})));
        userService.create(new UserRequest(new User("member", "member", "mail2@eg.com", "87654321", new String[] {"MEMBER"})));
    }
}
