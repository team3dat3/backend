package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.dto.movie.MovieResponse;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

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
        .prodYear(1901)
        .ageLimit(12)
        .description("blabla")
        .genre("Horror")
        .runtime("142 min")
        .build();

    movie2 = Movie.builder().title("2")
        .director("2")
        .actors("2")
        .prodYear(1902)
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
    Movie movie3 = Movie.builder().title("3")
        .director("3")
        .actors("3")
        .prodYear(1903)
        .ageLimit(12)
        .description("blablabla")
        .genre("Scifi")
        .runtime("142 min")
        .build();
    MovieRequest movieRequest = new MovieRequest(movie3);
    MovieResponse movieResponse = movieService.addMovie(movieRequest);
    assertEquals("3", movieResponse.getTitle());
  }

  @Test
  void deleteMovie() {
    Movie movie4 = Movie.builder().title("4")
        .director("4")
        .actors("4")
        .prodYear(1904)
        .ageLimit(12)
        .description("blablabla")
        .genre("Scifi")
        .runtime("142 min")
        .build();
    MovieRequest movieRequest = new MovieRequest(movie4);
    movieService.addMovie(movieRequest);
    movieService.deleteMovie(movieRequest);
    assertThrows(ResponseStatusException.class, () -> {
      movieService.findMovieByTitle("4");});
  }

  @Test
  void findMovieByTitle() {
    MovieResponse findMovie = movieService.findMovieByTitle(movie1.getTitle());
    assertEquals(movie1.getTitle(), findMovie.getTitle());
  }

  @Test
  void moviesByGenre() {
    List<MovieResponse> findMovie = movieService.moviesByGenre("Scifi");
    assertEquals("Scifi", findMovie.get(0).getGenre());
  }

  @Test
  void updateMovie() {
    Movie movieToUpdate = Movie.builder().title("1")
        .director("4")
        .actors("4")
        .prodYear(1904)
        .ageLimit(12)
        .description("blablabla")
        .genre("Scifi")
        .runtime("142 min")
        .build();

    MovieRequest movieRequest = new MovieRequest(movieToUpdate);
    movieService.update(movieRequest);
    MovieResponse movieResponse = movieService.findMovieByTitle("1");
    assertEquals(movieResponse.getDirector(), movieToUpdate.getDirector());
  }

}