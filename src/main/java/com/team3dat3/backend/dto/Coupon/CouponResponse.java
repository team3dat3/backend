package com.team3dat3.backend.dto.Coupon;

import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {
    private int id;
    private String name;
    private double discount;
    private String username;
    private double cost;
    private boolean used;

    public CouponResponse(Coupon coupon){
        this.id = coupon.getId();
        this.name = coupon.getName();
        this.discount = coupon.getDiscount();
        this.username = coupon.getUser() != null ? coupon.getUser().getUsername() : "";
        this.cost = coupon.getCost();
        this.used = coupon.isUsed();
    }
}
