package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.user.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.UserRepository;
import com.team3dat3.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.print.attribute.standard.Media;

@DataJpaTest
public class UserControllerTest {
    @Autowired
    UserRepository userRepository;
    UserService userService;
    UserController userController;

    private User user1;
    private User user2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userRepository);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user1 = userRepository.save(new User("user1", "email1", "phone1"));
        user2 = userRepository.save(new User("user2", "email2", "phone2"));
    }

    @Test
    void testFindAll() throws Exception {
        System.out.println(mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))));

    }

    @Test
    void testFind() throws Exception {
        System.out.println(mockMvc.perform(get("/v1/users/" + user1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user1.getId()))));


    }

    @Test
    void testCreate() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("testEmail");
        userRequest.setUsername("testUsername");
        userRequest.setPhoneNumber("testPhoneNumber");
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(not(0))));
    }

    @Test
    void testUpdate() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user2.getId());
        userRequest.setUsername("hej");
        mockMvc.perform(patch("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user2.getId())))
                .andExpect(jsonPath("$.username", is("hej")));
    }

    @Test
    void testDelete() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user2.getId());
        mockMvc.perform(delete("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isOk());
    }
}
