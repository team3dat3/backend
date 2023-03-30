package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AchievementServiceTest {
    @Autowired
    AchievementRepository achievementRepository;
    AchievementService achievementService;

    @Autowired
    UserRepository userRepository;

    private Achievement achievement1;
    private Achievement achievement2;

    private User user1;

    @BeforeEach
    void beforeEach(){
        achievementService = new AchievementService(achievementRepository, userRepository);
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "87654321", new String[] {"MEMBER"}));
        achievement1 = achievementRepository.save(new Achievement(user1, "name1", "description1", true));
        achievement2 = achievementRepository.save(new Achievement(user1, "name2", "description2", true));
    }

    @Test
    public void testFindAll(){
        List<AchievementResponse> achievementResponses = achievementService.findAll();
        assertEquals(achievementResponses.size(), 2);
    }

    @Test
    public void testFind(){
        AchievementResponse achievementResponse = achievementService.find(achievement1.getId());
        assertEquals(achievement1.getId(), achievementResponse.getId());
    }

    @Test
    public void testCreate(){
        /*AchievementRequest achievementRequest = new AchievementRequest();
        AchievementResponse achievementResponse = achievementService.create(achievementRequest);
        assertNotEquals(0, achievementResponse.getId());*/
    }

    @Test
    public void testUpdate(){
        /*AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setUnlocked(true);
        achievementRequest.setName("test");
        achievementRequest.setId(achievement2.getId());
        AchievementResponse achievementResponse = achievementService.update(achievementRequest);
        assertTrue(achievementResponse.isUnlocked());
        assertEquals("test", achievementResponse.getName());
        assertEquals(achievementRequest.getId(), achievementResponse.getId());*/
    }

    @Test
    public void testDelete(){
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setId(achievement2.getId());
        achievementService.delete(achievementRequest);
        assertThrows(ResponseStatusException.class, () -> {
            achievementService.find(3);
        });
    }

    @Test
    void testFindUserAchievements() {
        List<AchievementResponse> achievementResponses = achievementService.findUserAchievements(user1.getUsername());
        assertEquals(2, achievementResponses.size());
    }

    @Test
    void testFindUserAchievement() {
        AchievementResponse achievementResponse = achievementService.findUserAchievement(user1.getUsername(), achievement1.getId());
        assertEquals(achievement1.getId(), achievementResponse.getId());
    }
}
