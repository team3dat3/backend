package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.ShowDateTime;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowDateTimeRepository extends JpaRepository<ShowDateTime, Integer> {

  @Query("SELECT d FROM ShowDateTime d WHERE d.show.id = :id")
  List<ShowDateTime> findShowDatesShow(@Param("id") int id);
}
