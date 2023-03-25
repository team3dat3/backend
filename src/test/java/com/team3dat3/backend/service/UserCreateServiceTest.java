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
        userService = new UserService(userRepository, achievementService);
        userCreateService = new UserCreateService(userService, achievementService);

        user = userRepository.save(new User("testUsername", "testEmail", "testPhoneNumber"));
    }

    @Test
    void createUserWithAchievementsTest() {
        UserRequest userRequest = new UserRequest("testUsername", "testEmail", "testPhoneNumber");
        userRequest.setId(user.getId());
        UserResponse userResponse = userCreateService.createUserWithAchievements(userRequest);
        assertNotNull(userResponse.getAchievements());
    }
}
