package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserCreateServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AchievementRepository achievementRepository;
    UserService userService;
    AchievementService achievementService;
    UserCreateService userCreateService;

    User user;

    @BeforeEach
    void beforeEach() {
        achievementService = new AchievementService(achievementRepository, userRepository);
        userService = new UserService(userRepository, new BCryptPasswordEncoder());
        userCreateService = new UserCreateService(userService, achievementService);
        user = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "12345678", new String[] {"ADMIN"}));
    }

    @Test
    void createUserWithAchievementsTest() {
        UserRequest userRequest = new UserRequest(user);
        userRequest.setUsername("newUser");
        userRequest.setEmail("new@eg.com");
        userRequest.setPhoneNumber("12121212");
        UserResponse userResponse = userCreateService.createUserWithAchievements(userRequest);
        assertNotNull(userResponse.getAchievements());
    }
}
