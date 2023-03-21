package com.team3dat3.backend.dto.user;

import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<Reservation> reservations;
    private List<Achievement> achievements;
    private List<Coupon> coupons;

    public UserResponse(User user, boolean includeAll) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.reservations = user.getReservations();
        if (includeAll) {
            this.achievements = user.getAchievements();
            this.coupons = user.getCoupons();
        }
    }
}
