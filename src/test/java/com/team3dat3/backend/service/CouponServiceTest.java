package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.repository.CouponRepository;
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

    private Coupon coupon1;
    private Coupon coupon2;

    @BeforeEach
    void beforeEach() {
        couponService = new CouponService(couponRepository);
        coupon1 = couponRepository.save(new Coupon("coupon1", 1, 1));
        coupon2 = couponRepository.save(new Coupon("coupon2", 2, 2));
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
        CouponRequest couponRequest = new CouponRequest("testCoupon", 0, 0);
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
    void testDelete() {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setId(coupon2.getId());
        couponService.delete(couponRequest);
        assertThrows(ResponseStatusException.class, () -> {
            couponService.find(3);
        });
    }
}

