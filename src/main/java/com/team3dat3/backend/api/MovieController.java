package com.team3dat3.backend.api;

import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.dto.movie.MovieResponse;
import com.team3dat3.backend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

  private MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  List<MovieResponse> getMovies(){return movieService.getMovies();}

  @GetMapping("genre/{genre}")
  List<MovieResponse> getMoviesByGenre(@PathVariable String genre){return movieService.moviesByGenre(genre);}

  @GetMapping("/{title}")
  MovieResponse getMovieByTitle(@PathVariable String title){return movieService.findMovieByTitle(title);}

  @PostMapping()
  MovieResponse addMovie(@RequestBody MovieRequest body){return movieService.addMovie(body);}

  @PatchMapping()
  MovieResponse editMovie(@RequestBody MovieRequest body){return movieService.update(body);}

  @DeleteMapping()
  void delete(@RequestBody MovieRequest body) {
    movieService.deleteMovie(body);
  }
}
