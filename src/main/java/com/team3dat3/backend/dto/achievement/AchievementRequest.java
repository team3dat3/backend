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
    private User user;
    private String name;
    private String description;
    private boolean unlocked;

    public void copyTo(Achievement achievement){
        achievement.setId(id);
        achievement.setUser(user);
        achievement.setName(name);
        achievement.setDescription(description);
        achievement.setUnlocked(unlocked);

    }

    public Achievement toAchievement()
    {
        return new Achievement(id, user, name,description, unlocked);
    }

    public AchievementRequest(User user, String name, String description, boolean unlocked){
        this.user = user;
        this.name = name;
        this.description = description;
        this.unlocked = unlocked;
    }
}
