package com.team3dat3.backend.dto.achievement;

import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementResponse {
    private int id;
    private String username;
    private String name;
    private String description;
    private boolean unlocked;

    public AchievementResponse(Achievement achievement){
        this.id = achievement.getId();
        this.username = achievement.getUser() != null ? achievement.getUser().getUsername() : "";
        this.name = achievement.getName();
        this.description = achievement.getDescription();
        this.unlocked = achievement.isUnlocked();
    }
}
