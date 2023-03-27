package com.team3dat3.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.dto.user.UserResponse;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.UserRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@DataJpaTest
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    AchievementService achievementService;
    UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userRepository, new BCryptPasswordEncoder());
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "12345678", new String[] {"ADMIN"}));
        user2 = userRepository.save(new User("user2", "pass2", "mail2@eg.com", "87654321", new String[] {"MEMBER"}));
    }

    @Test
    void testFindAll() {
        List<UserResponse> userResponses = userService.findAll();
        assertEquals(2, userResponses.size());
    }

    @Test
    void testFind() {
        UserResponse userResponse = userService.find(user1.getUsername());
        assertEquals(user1.getUsername(), userResponse.getUsername());
    }

    @Test
    void testCreate() {
        UserRequest userRequest = new UserRequest(user1);
        userRequest.setUsername("newUser");
        userRequest.setEmail("new@eg.com");
        userRequest.setPhoneNumber("12121212");
        UserResponse userResponse = userService.create(userRequest);
        assertEquals(userRequest.getUsername(), userResponse.getUsername());
        assertEquals(userRequest.getEmail(), userResponse.getEmail());
        assertEquals(userRequest.getPhoneNumber(), userResponse.getPhoneNumber());
    }

    @Test
    void testUpdate(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user2.getUsername());
        userRequest.setEmail("newEmail@eg.com");
        UserResponse userResponse = userService.update(userRequest);
        assertEquals(userRequest.getUsername(), userResponse.getUsername());
        assertEquals(userRequest.getEmail(), userResponse.getEmail());
    }

    @Test
    void testDelete(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user2.getUsername());
        userService.delete(userRequest);
        assertThrows(ResponseStatusException.class, () -> {
            userService.find(user2.getUsername());});
        }
    }
