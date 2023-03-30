package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.CouponRepository;
import com.team3dat3.backend.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@DataJpaTest
public class CouponServiceTest {
    @Autowired
    CouponRepository couponRepository;
    CouponService couponService;

    @Autowired
    UserRepository userRepository;

    private Coupon coupon1;
    private Coupon coupon2;

    private User user1;

    @BeforeEach
    void beforeEach() {
        couponService = new CouponService(couponRepository, userRepository);
        user1 = userRepository.save(new User("user1", "pass1", "mail1@eg.com", "87654321", new String[] {"MEMBER"}));
        coupon1 = couponRepository.save(new Coupon(0, "testCoupon1", 1, user1, 1, false));
        coupon2 = couponRepository.save(new Coupon(1, "testCoupon2", 2, user1, 2, false));
    }

    @Test
    void testFindAll() {
        List<CouponResponse> couponResponses = couponService.findAll();
        assertEquals(2, couponResponses.size());
    }

    @Test
    void testFind() {
        CouponResponse couponResponse = couponService.find(coupon1.getId());
        assertEquals(coupon1.getId(), couponResponse.getId());
    }

    @Test
    void testCreate() {
        CouponRequest couponRequest = new CouponRequest("testCoupon", "user", 0, 0);
        CouponResponse couponResponse = couponService.create(couponRequest);
        assertNotEquals(0, couponResponse.getId());
    }

    @Test
    void testUpdate() {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setId(coupon2.getId());
        couponRequest.setName("couponTest");
        CouponResponse couponResponse = couponService.update(couponRequest);
        assertEquals(couponRequest.getId(), couponResponse.getId());
        assertEquals(couponRequest.getName(), "couponTest");
    }

    @Test
    void testUse() {
        CouponResponse couponResponse = couponService.use(coupon2.getId());
        assertEquals(coupon2.getId(), couponResponse.getId());
        assertEquals(true, couponResponse.isUsed());
    }

    @Test
    void testDelete() {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setId(coupon2.getId());
        couponService.delete(couponRequest);
        assertThrows(ResponseStatusException.class, () -> {
            couponService.find(3);
        });
    }

    @Test
    void testFindUserCoupons() {
        List<CouponResponse> couponResponses = couponService.findUserCoupons(user1.getUsername());
        assertEquals(2, couponResponses.size());
    }

    @Test
    void testFindUserCoupon() {
        CouponResponse couponResponse = couponService.findUserCoupon(user1.getUsername(), coupon2.getId());
        assertEquals(coupon2.getId(), couponResponse.getId());
    }
}

