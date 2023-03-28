package com.team3dat3.backend.dto.user;

import com.team3dat3.backend.dto.authenticatable.AuthenticatableRequest;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.User;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends AuthenticatableRequest {

    /* Derived from AuthenticatableRequest
    @Id
    private int id;
    private String username;
    private String password;
    */

    private String email;
    private String phoneNumber;
    //private List<Achievement> achievements;
    //private List<Reservation> reservations;
    //private List<Coupon> coupons;

    public UserRequest(User user){
        super(user);
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        //this.achievements = user.getAchievements();
        //this.reservations = user.getReservations();
        //this.coupons = user.getCoupons();
    }

    public void copyTo(User user){
        super.copyTo(user);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        //user.setAchievements(achievements);
        //user.setReservations(reservations);
        //user.setCoupons(coupons);
    }

    public User toUser(){
        User user = new User();
        copyTo(user);
        return user;
    }
}
