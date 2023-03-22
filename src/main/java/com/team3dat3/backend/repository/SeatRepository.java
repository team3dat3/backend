package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat repository
 */
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
