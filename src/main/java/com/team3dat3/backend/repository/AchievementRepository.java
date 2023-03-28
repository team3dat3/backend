package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Achievement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    @Query("SELECT a FROM Achievement a WHERE a.user.username = :username")
    List<Achievement> findUserAchievements(@Param("username") String username);

    @Query("SELECT a FROM Achievement a WHERE a.id = :id AND a.user.username = :username")
    Optional<Achievement> findUserAchievement(@Param("username") String username, @Param("id") int id);
}
