package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater repository
 */
public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
