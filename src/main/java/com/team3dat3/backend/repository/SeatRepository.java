package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.ShowDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat repository
 */
public interface SeatRepository extends JpaRepository<Seat, Long> {

  @Query("SELECT s FROM Seat s WHERE s.seatRow.id = :id")
  List<Seat> findSeatsBySeatRowId(@Param("id") Long id);
}
