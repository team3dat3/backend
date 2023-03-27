package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;

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
}
