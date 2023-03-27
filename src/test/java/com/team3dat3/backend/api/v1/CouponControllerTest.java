package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.Coupon.CouponRequest;
import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.repository.CouponRepository;
import com.team3dat3.backend.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

@DataJpaTest
public class CouponControllerTest {
    @Autowired
    CouponRepository couponRepository;
    CouponService couponService;
    CouponController couponController;

    private Coupon coupon1;
    private Coupon coupon2;

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        couponService = new CouponService(couponRepository);
        couponController = new CouponController(couponService);
        mockMvc = MockMvcBuilders.standaloneSetup(couponController).build();
        coupon1 = couponRepository.save(new Coupon("coupon1", 1, 1));
        coupon2 = couponRepository.save(new Coupon("coupon2", 2, 2));
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(get("/v1/admin/coupons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testFind() throws Exception {
        mockMvc.perform(get("/v1/admin/coupons/" + coupon1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(coupon1.getId())));
    }

    @Test
    void testCreate() throws Exception {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setName("testCoupon");
        couponRequest.setDiscount(0);
        couponRequest.setCost(0);

        mockMvc.perform(post("/v1/admin/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(couponRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(not(0))));
    }

    @Test
    void testUpdate() throws Exception {
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setId(coupon2.getId());
        couponRequest.setName("testName");
        mockMvc.perform(patch("/v1/admin/coupons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(couponRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(coupon2.getId())))
                .andExpect(jsonPath("$.name", is("testName")));
    }

    @Test
    void testUse() throws Exception {
        mockMvc.perform(get("/v1/admin/coupons/" + coupon1.getId() + "/use"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(coupon1.getId())))
                .andExpect(jsonPath("$.used", is(true)));
    }

    @Test
    void testDelete() throws Exception{
        CouponRequest couponRequest = new CouponRequest();
        couponRequest.setId(coupon2.getId());
        mockMvc.perform(delete("/v1/admin/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(couponRequest)))
                .andExpect(status().isOk());
    }
}
