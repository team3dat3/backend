package com.team3dat3.backend.repository;

import java.time.LocalDateTime;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Short message repository
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team3dat3.backend.entity.ShortMessage;

@Repository
public interface ShortMessageRepository extends JpaRepository<ShortMessage, Integer> {
    @Query("SELECT COUNT(*) FROM ShortMessage WHERE createdDate >= :startOfDay AND createdDate < :endOfDay")
    int countShortMessagesCreatedToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
