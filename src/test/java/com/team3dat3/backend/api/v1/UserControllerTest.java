package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.service.AchievementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.UserRepository;
import com.team3dat3.backend.service.UserService;

@DataJpaTest
public class UserControllerTest {
    @Autowired
    UserRepository userRepository;
    AchievementService achievementService;
    UserService userService;
    UserController userController;

    private User user1;
    private User user2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userRepository, new BCryptPasswordEncoder());
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "12345678", new String[] {"ADMIN"}));
        user2 = userRepository.save(new User("user2", "pass2", "mail2@eg.com", "87654321", new String[] {"MEMBER"}));
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/v1/admin/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/v1/admin/users/" + user1.getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(user1.getUsername())));
    } 

    @Test
    void testCreate() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("testEmail");
        userRequest.setUsername("testUsername");
        userRequest.setPassword("testPassword");
        userRequest.setPhoneNumber("testPhoneNumber");
        userRequest.setRoles(Arrays.asList("MEMBER"));
        mockMvc.perform(post("/v1/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(userRequest.getUsername())));
    }

    @Test
    void testUpdate() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user1.getUsername());
        userRequest.setEmail("update@Email.com");
        mockMvc.perform(put("/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is(user1.getUsername())))
                .andExpect(jsonPath("$.email", is(user1.getEmail())));
    }

    @Test
    void testDelete() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(user2.getUsername());
        mockMvc.perform(delete("/v1/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk());
    }
}
