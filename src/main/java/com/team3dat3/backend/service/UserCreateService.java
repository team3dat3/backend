package com.team3dat3.backend.service;

import org.springframework.stereotype.Service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;

@Service
public class UserCreateService {
    private UserService userService;
    private AchievementService achievementService;

    public UserCreateService(UserService userService, AchievementService achievementService){
        this.userService = userService;
        this.achievementService = achievementService;
    }

    public UserResponse createUserWithAchievements(UserRequest userRequest){
        UserResponse userResponse = userService.create(userRequest);
        UserResponse userResponseWithAchievements = achievementService.initiateAchievements(userResponse.getUsername());
        return userResponseWithAchievements;
    }

    public UserResponse registerWithAchievements(UserRequest userRequest){
        UserResponse userResponse = userService.register(userRequest);
        UserResponse userResponseWithAchievements = achievementService.initiateAchievements(userResponse.getUsername());
        return userResponseWithAchievements;
    }
}
