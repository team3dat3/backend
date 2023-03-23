package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/coupons")
@RestController
public class CouponController {
    private CouponService couponService;

    public CouponController(CouponService couponService){
        this.couponService = couponService;
    }

    @GetMapping
    public List<CouponResponse> findAll() {
        return couponService.findAll();
    }

    @GetMapping ("/{id}")
    CouponResponse find(@PathVariable("id") int id){
        return couponService.find(id);
    }

    @PostMapping
    public CouponResponse create(@RequestBody CouponRequest couponRequest){
        return couponService.create(couponRequest);
    }

    @PatchMapping
    public CouponResponse update(@RequestBody CouponRequest couponRequest){
        return couponService.update(couponRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody CouponRequest couponRequest){
        couponService.delete(couponRequest);
    }
}
