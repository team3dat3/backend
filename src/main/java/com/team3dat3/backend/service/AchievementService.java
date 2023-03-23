package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.repository.AchievementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository){
        this.achievementRepository = achievementRepository;
    }

    public List<AchievementResponse> findAll(){
        return achievementRepository
                .findAll()
                .stream()
                .map(a -> new AchievementResponse(a))
                .collect(Collectors.toList());
    }

    public AchievementResponse find(int id){
        Achievement achievement = achievementRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new AchievementResponse(achievement);
    }

    public AchievementResponse create(AchievementRequest achievementRequest){
        Achievement achievement = achievementRepository.save(
                achievementRequest.toAchievement()
        );
        return new AchievementResponse(achievementRepository.save(achievement));
    }

    public AchievementResponse update(AchievementRequest achievementRequest){
        Achievement achievement = achievementRepository
                .findById(achievementRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        achievementRequest.copyTo(achievement);
        return new AchievementResponse(achievementRepository.save(achievement));
    }

    public void delete(AchievementRequest achievementRequest){
        Achievement achievement = achievementRepository
                .findById(achievementRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        achievementRepository.delete(achievement);
    }

    public void movieStreak(){

    }
}
