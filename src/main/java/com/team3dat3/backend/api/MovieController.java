package com.team3dat3.backend.api;

import com.team3dat3.backend.dto.MovieRequest;
import com.team3dat3.backend.dto.MovieResponse;
import com.team3dat3.backend.service.MovieService;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/{title}")
  MovieResponse getMovieByTitle(@PathVariable String title){return movieService.findMovieByTitle(title);}

  @PostMapping()
  MovieResponse addMovie(@RequestBody MovieRequest body){return movieService.addMovie(body);}

  @PutMapping("/{title}")
  ResponseEntity<Boolean> editMovie(@RequestBody MovieRequest body, String title){return movieService.updateMovie(body, title);}

  @PatchMapping("/")
  ResponseEntity<Boolean> editDescription(@PathVariable String description, @PathVariable String title){return movieService.updateDescription(title, description);}

  @DeleteMapping("/{id}")
  ResponseEntity<Boolean> deleteMovieByTitle(@PathVariable String title) {
    return movieService.deleteMovie(title);
  }
}
