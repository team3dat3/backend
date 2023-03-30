package com.team3dat3.backend.dto.achievement;

import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AchievementRequest {

    private int id;
    private String username;
    private String name;
    private String description;
    private boolean unlocked;

    public void copyTo(Achievement achievement) {
        achievement.setId(id);
        achievement.setName(name);
        achievement.setDescription(description);
        achievement.setUnlocked(unlocked);
    }

    public Achievement toAchievement() {
        Achievement achievement = new Achievement();
        copyTo(achievement);
        return achievement;
    }

    public AchievementRequest(
        User user,
        String name,
        String description,
        boolean unlocked
    ) {
        this.username = user != null ? user.getUsername() : "";
        this.name = name;
        this.description = description;
        this.unlocked = unlocked;
    }
}
