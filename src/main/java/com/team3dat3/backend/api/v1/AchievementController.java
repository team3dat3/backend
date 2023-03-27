package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.service.AchievementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/achievements")
@RestController
public class AchievementController {
    AchievementService achievementService;

    public AchievementController(AchievementService achievementService){
        this.achievementService = achievementService;
    }

    @GetMapping
    public List<AchievementResponse> findAll(){
        return achievementService.findAll();
    }

    @GetMapping("/{id}")
    public AchievementResponse find(@PathVariable("id") int id){
        return achievementService.find(id);
    }

    @PostMapping
    public AchievementResponse create(@RequestBody AchievementRequest achievementRequest){
        return achievementService.create(achievementRequest);
    }

    @PatchMapping
    public AchievementResponse update(@RequestBody AchievementRequest achievementRequest){
        return achievementService.update(achievementRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody AchievementRequest achievementRequest){
        achievementService.delete(achievementRequest);
    }
}
