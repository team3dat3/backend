package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.achievement.AchievementRequest;
import com.team3dat3.backend.dto.achievement.AchievementResponse;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.AchievementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<AchievementResponse> findAll() {
        return achievementRepository
                .findAll()
                .stream()
                .map(a -> new AchievementResponse(a))
                .collect(Collectors.toList());
    }

    public AchievementResponse find(int id) {
        Achievement achievement = achievementRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new AchievementResponse(achievement);
    }

    public AchievementResponse create(AchievementRequest achievementRequest) {
        Achievement achievement = achievementRepository.save(
                achievementRequest.toAchievement()
        );
        return new AchievementResponse(achievementRepository.save(achievement));
    }

    public AchievementResponse update(AchievementRequest achievementRequest) {
        Achievement achievement = achievementRepository
                .findById(achievementRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        achievementRequest.copyTo(achievement);
        return new AchievementResponse(achievementRepository.save(achievement));
    }

    public void delete(AchievementRequest achievementRequest) {
        Achievement achievement = achievementRepository
                .findById(achievementRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        achievementRepository.delete(achievement);
    }

    public void initiateAchievements(int id) {
        User user = new User();
        user.setId(id);
        System.out.println("initiating achievements on user with username: " + user.getUsername());
        if (user.getAchievements().size() == 0) {
            user.getAchievements().add(new Achievement(user, "The Rookie", "You bought your first ticket!", false));
            user.getAchievements().add(new Achievement(user, "The Fifth Reel", "Your fifth movie!", false));
            user.getAchievements().add(new Achievement(user, "Double Digits!", "Ten movies, right on!", false));
            user.getAchievements().add(new Achievement(user, "The Silver Screen", "Your 20th movie", false));
            user.getAchievements().add(new Achievement(user, "Half-Century Club", "50 movies?!?!", false));
            user.getAchievements().add(new Achievement(user, "Ultimate Cinephile", "100 movie club", false));
        }
        System.out.println("Achievement List: " + user.getAchievements().toString());
    }

    public void checkMovieStreak(User user) {
        if (user.getReservations().size() >= 1) {
            user.getAchievements().get(0).setUnlocked(true);
        } else if (user.getReservations().size() >= 5) {
            user.getAchievements().get(1).setUnlocked(true);
        } else if (user.getReservations().size() >= 10) {
            user.getAchievements().get(2).setUnlocked(true);
        } else if (user.getReservations().size() >= 20) {
            user.getAchievements().get(3).setUnlocked(true);
        } else if (user.getReservations().size() >= 50) {
            user.getAchievements().get(4).setUnlocked(true);
        } else if (user.getReservations().size() >= 100) {
            user.getAchievements().get(5).setUnlocked(true);
        }
    }
}
