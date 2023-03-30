package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.CouponRepository;
import com.team3dat3.backend.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CouponService {

    private CouponRepository couponRepository;

    private UserRepository userRepository;

    public CouponService(
        CouponRepository couponRepository,
        UserRepository userRepository
    ) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
    }

    public List<CouponResponse> findAll() {
        return couponRepository
        .findAll()
        .stream()
        .map(c -> new CouponResponse(c))
        .collect(Collectors.toList());
    }

    public CouponResponse find(int id) {
        Coupon coupon = couponRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new CouponResponse(coupon);
    }

    public CouponResponse create(CouponRequest couponRequest) {
        Coupon coupon = couponRequest.toCoupon();
        coupon.setUser(findUser(couponRequest.getUsername()));
        coupon = couponRepository.save(coupon);
        return new CouponResponse(coupon);
    }

    public CouponResponse use(int id) {
        Coupon coupon = couponRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        coupon.setUsed(true);
        return new CouponResponse(couponRepository.save(coupon));
    }

    public CouponResponse update(CouponRequest couponRequest) {
        Coupon coupon = couponRepository
        .findById(couponRequest.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        couponRequest.copyTo(coupon);
        coupon.setUser(findUser(couponRequest.getUsername()));
        return new CouponResponse(couponRepository.save(coupon));
    }

    public void delete(CouponRequest couponRequest) {
        Coupon coupon = couponRepository
        .findById(couponRequest.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        couponRepository.delete(coupon);
    }

    public List<CouponResponse> findUserCoupons(String username) {
        return couponRepository
        .findUserCoupons(username)
        .stream()
        .map(c -> new CouponResponse(c))
        .collect(Collectors.toList());
    }

    public CouponResponse findUserCoupon(String username, int id) {
        Coupon coupon = couponRepository
        .findUserCoupon(username, id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new CouponResponse(coupon);
    }

    private User findUser(String username) {
        return userRepository
        .findById(username)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
