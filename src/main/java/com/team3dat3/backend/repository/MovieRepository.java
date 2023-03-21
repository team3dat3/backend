package com.team3dat3.backend.repository;

import com.team3dat3.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
  boolean existsByTitle(String title);

  List<Movie> findMoviesByGenre(String genre);
}
