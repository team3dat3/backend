package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.service.AchievementService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
public class AchievementController {
    AchievementService achievementService;

    public AchievementController(AchievementService achievementService){
        this.achievementService = achievementService;
    }

    @GetMapping("/member/achievements")
    public List<AchievementResponse> findUserAchievements(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        return achievementService.findUserAchievements(username);
    }

    @GetMapping("/member/achievements/{id}")
    public AchievementResponse findUserAchievement(@AuthenticationPrincipal Jwt jwt, @PathVariable("id") int id) {
        String username = jwt.getSubject();
        return achievementService.findUserAchievement(username, id);
    }

    @GetMapping("/admin/achievements")
    public List<AchievementResponse> findAll(){
        return achievementService.findAll();
    }

    @GetMapping("/admin/achievements/{id}")
    public AchievementResponse find(@PathVariable("id") int id){
        return achievementService.find(id);
    }

    @PostMapping("/admin/achievements")
    public AchievementResponse create(@RequestBody AchievementRequest achievementRequest){
        return achievementService.create(achievementRequest);
    }

    @PutMapping("/admin/achievements")
    public AchievementResponse update(@RequestBody AchievementRequest achievementRequest){
        return achievementService.update(achievementRequest);
    }

    @DeleteMapping("/admin/achievements")
    public void delete(@RequestBody AchievementRequest achievementRequest){
        achievementService.delete(achievementRequest);
    }
}
