package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.Coupon.CouponResponse;
import com.team3dat3.backend.service.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
public class CouponController {
    private CouponService couponService;

    public CouponController(CouponService couponService){
        this.couponService = couponService;
    }

    @GetMapping("/authenticated/coupons")
    public List<CouponResponse> findAll() {
        return couponService.findAll();
    }

    @GetMapping ("/authenticated/coupons/{id}")
    CouponResponse find(@PathVariable("id") int id){
        return couponService.find(id);
    }

    @PostMapping("/admin/coupons")
    public CouponResponse create(@RequestBody CouponRequest couponRequest){
        return couponService.create(couponRequest);
    }

    @PatchMapping("/admin/coupons")
    public CouponResponse update(@RequestBody CouponRequest couponRequest){
        return couponService.update(couponRequest);
    }

    @DeleteMapping("/admin/coupons")
    public void delete(@RequestBody CouponRequest couponRequest){
        couponService.delete(couponRequest);
    }
}
