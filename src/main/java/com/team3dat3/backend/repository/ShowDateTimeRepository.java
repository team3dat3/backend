package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.ShowDateTime;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowDateTimeRepository extends JpaRepository<ShowDateTime, DateTime> {
}
