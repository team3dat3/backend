package com.team3dat3.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.repository.MovieRepository;
import com.team3dat3.backend.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * Author: Thomas S. Andersen
 * Date: 21/03/2023
 * Description: Movie controller test
 */

@DataJpaTest
class MovieControllerTest {

  @Autowired
  MovieRepository movieRepository;

  MovieService movieService;
  MovieController movieController;

  private Movie movie1;
  private Movie movie2;

  MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    movieService = new MovieService(movieRepository);
    movieController = new MovieController(movieService);
    mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();

    movie1 = Movie.builder().title("1")
        .director("1")
        .actors("1")
        .prodYear(1901)
        .ageLimit(12)
        .description("blabla")
        .genre(Arrays.asList(new String[]{"Horror", "Thriller"}))
        .runtime("142 min")
        .build();

    movie2 = Movie.builder().title("2")
        .director("2")
        .actors("2")
        .prodYear(1902)
        .ageLimit(12)
        .description("blablabla")
        .genre(Arrays.asList(new String[]{"Scifi"}))
        .runtime("142 min")
        .build();

    movieRepository.save(movie1);
    movieRepository.save(movie2);
  }
  @Test
  void getMovies() throws Exception{
    mockMvc.perform(get("/api/movies"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void getMoviesByGenre() throws Exception{
    mockMvc.perform(get("/api/movies/genre/" + movie1.getGenre().get(0)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  void getMovieByTitle() throws Exception{
    mockMvc.perform(get("/api/movies/" + movie1.getTitle()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", is(movie1.getTitle())));
  }

  @Test
  void addMovie() throws Exception{
    Movie movie3 = Movie.builder().title("3")
        .director("3")
        .actors("3")
        .prodYear(1903)
        .ageLimit(12)
        .description("blablabla")
        .genre(Arrays.asList(new String[]{"Scifi"}))
        .runtime("142 min")
        .build();
    MovieRequest movieRequest = new MovieRequest(movie3);
    mockMvc.perform(post("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(movieRequest)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", is("3")));
  }

  @Test
  void editMovie() throws Exception{
    Movie movie3 = Movie.builder()
        .title("2")
        .director("3")
        .actors("3")
        .prodYear(1903)
        .ageLimit(12)
        .description("blablabla")
        .genre(Arrays.asList(new String[]{"Scifi"}))
        .runtime("142 min")
        .build();
    MovieRequest movieRequest = new MovieRequest(movie3);
    mockMvc.perform(patch("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(movieRequest)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title", is("2")));
  }


  @Test
  void deleteMovieByTitle() throws Exception{
    MovieRequest movieRequest = new MovieRequest(movie2);
    mockMvc.perform(delete("/api/movies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(movieRequest)))
        .andExpect(status().isOk());
  }
}