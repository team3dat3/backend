package com.team3dat3.backend.dto.Coupon;

import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponRequest {
    private int id;
    private String name;
    private double discount;
    private User user;
    private double cost;

    public static Coupon getFromCouponRequest(CouponRequest cr){
        return Coupon.builder()
                .name(cr.name)
                .discount(cr.discount)
                .user(cr.user)
                .cost(cr.cost)
                .build();
    }

    public void copyTo(Coupon coupon){
        coupon.setId(id);
        coupon.setName(name);
        coupon.setDiscount(discount);
        coupon.setUser(user);
        coupon.setCost(cost);
    }

    public Coupon toCoupon(){
        return new Coupon(id, name, discount, user, cost);
    }

    public CouponRequest(String name, double discount, double cost){
        this.name = name;
        this.discount = discount;
        this.user = user;
        this.cost = cost;
    }
}
