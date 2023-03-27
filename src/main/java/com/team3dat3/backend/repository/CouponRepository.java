package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Coupon;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Query("SELECT c FROM Coupon c WHERE c.user.username = :username")
    List<Coupon> findUserCoupons(@Param("username") String username);

    @Query("SELECT c FROM Coupon c WHERE c.id = :id AND c.user.username = :username")
    Optional<Coupon> findUserCoupon(@Param("username") String username, @Param("id") int id);
}
