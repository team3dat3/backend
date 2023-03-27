package com.team3dat3.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team3dat3.backend.entity.Reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation repository
 */

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    @Query("SELECT r FROM Reservation r WHERE r.user.username = :username")
    List<Reservation> findUserReservations(@Param("username") String username);

    @Query("SELECT r FROM Reservation r WHERE r.id = :id AND r.user.username = :username")
    Optional<Reservation> findUserReservation(@Param("username") String username, @Param("id") int id);
}
