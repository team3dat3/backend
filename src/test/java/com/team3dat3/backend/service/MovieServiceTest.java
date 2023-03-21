package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.MovieResponse;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieServiceTest {

  MovieService movieService;

  @Autowired
  MovieRepository movieRepository;

  private Movie movie1;

  private Movie movie2;

  @BeforeEach
  void beforeEach(){
    movieService = new MovieService(movieRepository);
    movie1 = Movie.builder().title("1")
        .director("1")
        .actors("1")
        .year(1901)
        .ageLimit(12)
        .description("blabla")
        .genre("Horror")
        .runtime("142 min")
        .build();

    movie2 = Movie.builder().title("2")
        .director("2")
        .actors("2")
        .year(1902)
        .ageLimit(12)
        .description("blablabla")
        .genre("Scifi")
        .runtime("142 min")
        .build();

    movieRepository.save(movie1);
    movieRepository.save(movie2);
  }


  @Test
  void getMovies() {
    List<MovieResponse> movieResponses = movieService.getMovies();
    assertEquals(2, movieResponses.size());
  }

  @Test
  void addMovie() {
  }

  @Test
  void deleteMovie() {
  }

  @Test
  void findMovieByTitle() {
  }

  @Test
  void moviesByGenre() {
  }

  @Test
  void updateMovie() {
  }

  @Test
  void updateDescription() {
  }
}