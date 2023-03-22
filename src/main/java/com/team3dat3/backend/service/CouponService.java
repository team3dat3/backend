package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.repository.CouponRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {
    private CouponRepository couponRepository;
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<CouponResponse> findAll(){
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

    public CouponResponse create(CouponRequest couponRequest){
        Coupon coupon = couponRepository.save(
                couponRequest.toCoupon()
        );
        return new CouponResponse(coupon);
    }

    public CouponResponse update(CouponRequest couponRequest){
        Coupon coupon = couponRepository
                .findById(couponRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        couponRequest.copyTo(coupon);
        return new CouponResponse(couponRepository.save(coupon));
    }

    public void delete(CouponRequest couponRequest){
        Coupon coupon = couponRepository
                .findById(couponRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        couponRepository.delete(coupon);
    }
}
