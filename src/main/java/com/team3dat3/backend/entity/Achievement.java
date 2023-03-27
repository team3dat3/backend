package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "name", length = 50)
    private String name;
    
    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "unlocked", length = 50)
    @Builder.Default
    private boolean unlocked = false;

    public Achievement(User user, String name, String description, boolean unlocked){
        this.user = user;
        this.name = name;
        this.description = description;
        this.unlocked = unlocked;
    }
}
