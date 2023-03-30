package com.team3dat3.backend.entity;

import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.service.AchievementService;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Authenticatable {

    /* Derived from Authenticatable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;
    */

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phoneNumber", length = 50, nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coupon> coupons;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public User(String username, String password, String email, String phoneNumber, String[] roles) {
        super(username, password, roles);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
