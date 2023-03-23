package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.repository.CouponRepository;
import com.team3dat3.backend.service.AchievementService;
import com.team3dat3.backend.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

@DataJpaTest
public class AchievementControllerTest {
    @Autowired
    AchievementRepository achievementRepository;
    AchievementController achievementController;
    AchievementService achievementService;

    private Achievement achievement1;
    private Achievement achievement2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        achievementService = new AchievementService(achievementRepository);
        achievementController = new AchievementController(achievementService);
        mockMvc = MockMvcBuilders.standaloneSetup(achievementController).build();

        achievement1 = achievementRepository.save(new Achievement());
        achievement2 = achievementRepository.save(new Achievement());
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/v1/achievements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/v1/achievements/" + achievement1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(achievement1.getId())));
    }

    @Test
    void testCreate() throws Exception {
        AchievementRequest achievementRequest = new AchievementRequest();
        mockMvc.perform(post("/v1/achievements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(achievementRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(not(0))));
    }

    @Test
    void testUpdate() throws Exception {
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setId(achievement2.getId());
        achievementRequest.setName("testName");
        mockMvc.perform(patch("/v1/achievements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(achievementRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(achievement2.getId())))
                .andExpect(jsonPath("$.name", is("testName")));
    }

    @Test
    void testDelete() throws Exception {
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setId(achievement2.getId());
        mockMvc.perform(delete("/v1/achievements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(achievementRequest)))
                .andExpect(status().isOk());
    }
}
