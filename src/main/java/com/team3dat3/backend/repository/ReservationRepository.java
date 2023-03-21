package com.team3dat3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team3dat3.backend.entity.Reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation repository
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
