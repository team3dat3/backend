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
    private String username;
    private double cost;
    private boolean used;

    public static Coupon getFromCouponRequest(CouponRequest cr) {
        return Coupon
        .builder()
        .name(cr.name)
        .discount(cr.discount)
        .cost(cr.cost)
        .build();
    }

    public void copyTo(Coupon coupon) {
        coupon.setId(id);
        coupon.setName(name);
        coupon.setDiscount(discount);
        coupon.setCost(cost);
        coupon.setUsed(used);
    }

    public Coupon toCoupon() {
        Coupon coupon = new Coupon();
        copyTo(coupon);
        return coupon;
    }

    public CouponRequest(String name, String username, double discount, double cost) {
        this.name = name;
        this.discount = discount;
        this.username = username;
        this.cost = cost;
    }
}
