package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.entity.Achievement;
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
    UserRepository userRepository;

    private Achievement achievement1;
    private Achievement achievement2;

    @BeforeEach
    void beforeEach(){
        achievementService = new AchievementService(achievementRepository, userRepository);
        achievement1 = achievementRepository.save(new Achievement());
        achievement2 = achievementRepository.save(new Achievement());
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
        AchievementRequest achievementRequest = new AchievementRequest();
        AchievementResponse achievementResponse = achievementService.create(achievementRequest);
        assertNotEquals(0, achievementResponse.getId());
    }

    @Test
    public void testUpdate(){
        AchievementRequest achievementRequest = new AchievementRequest();
        achievementRequest.setUnlocked(true);
        achievementRequest.setName("test");
        achievementRequest.setId(achievement2.getId());
        AchievementResponse achievementResponse = achievementService.update(achievementRequest);
        assertTrue(achievementResponse.isUnlocked());
        assertEquals("test", achievementResponse.getName());
        assertEquals(achievementRequest.getId(), achievementResponse.getId());
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
}
