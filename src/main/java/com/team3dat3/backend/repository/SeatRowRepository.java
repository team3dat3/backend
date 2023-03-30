package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.ShowDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRowRepository extends JpaRepository<SeatRow, Long> {

  @Query("SELECT s FROM SeatRow s WHERE s.theater.id = :id")
  List<SeatRow> findByTheaterId(@Param("id") Long id);
}
