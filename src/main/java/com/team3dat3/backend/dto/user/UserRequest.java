package com.team3dat3.backend.dto.user;

import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<Achievement> achievements;
    private List<Reservation> reservations;
    private List<Coupon> coupons;

    public static User getFromUserRequest(UserRequest ur){
        return User.builder()
                .username(ur.username)
                .email(ur.email)
                .phoneNumber(ur.phoneNumber)
                .reservations(ur.reservations)
                .achievements(ur.achievements)
                .coupons(ur.coupons)
                .build();
    }

    public void copyTo(User user){
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAchievements(achievements);
        user.setReservations(reservations);
        user.setCoupons(coupons);
    }

    public User toUser(){
        return new User(id, username, email, phoneNumber, reservations, achievements, coupons);
    }

    public UserRequest(String username, String email, String phoneNumber){
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
