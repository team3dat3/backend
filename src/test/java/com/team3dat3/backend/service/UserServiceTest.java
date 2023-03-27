package com.team3dat3.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        userService = new UserService(userRepository, achievementService);
        user1 = userRepository.save(new User("user1", "email1", "phone1"));
        user2 = userRepository.save(new User("user2", "email2", "phone2"));
    }

    @Test
    void testFindAll() {
        List<UserResponse> userResponses = userService.findAll();
        assertEquals(2, userResponses.size());
    }

    @Test
    void testFind() {
        UserResponse userResponse = userService.find(user1.getId());
        assertEquals(user1.getId(), userResponse.getId());
    }

    @Test
    void testCreate() {
        UserRequest userRequest = new UserRequest("testUsername", "testEmail", "testPhoneNumber");
        UserResponse userResponse = userService.create(userRequest);
        assertNotEquals(0, userResponse.getId());
    }

    @Test
    void testUpdate(){
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user2.getId());
        userRequest.setUsername("updatedUserName");
        UserResponse userResponse = userService.update(userRequest);
        assertEquals(userRequest.getId(), userResponse.getId());
        assertEquals(userRequest.getUsername(), "updatedUserName");
    }

    @Test
    void testDelete(){
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user2.getId());
        userService.delete(userRequest);
        assertThrows(ResponseStatusException.class, () -> {
            userService.find(3);});
        }
    }
