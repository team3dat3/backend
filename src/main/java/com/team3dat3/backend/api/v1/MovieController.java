package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.movie.MovieRequest;
import com.team3dat3.backend.dto.movie.MovieResponse;
import com.team3dat3.backend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 21/03/2023
 * Description: MovieController
 */

@RestController
@RequestMapping("/v1")
public class MovieController {

  private MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/anonymous/movies")
  List<MovieResponse> getMovies(){return movieService.getMovies();}

  @GetMapping("/anonymous/movies/genre/{genre}")
  List<MovieResponse> getMoviesByGenre(@PathVariable String genre){return movieService.moviesByGenre(genre);}

  @GetMapping("/anonymous/movies/{title}")
  MovieResponse getMovieByTitle(@PathVariable String title){return movieService.findMovieByTitle(title);}

  @PostMapping("/admin/movies")
  MovieResponse addMovie(@RequestBody MovieRequest body){return movieService.addMovie(body);}

  @PutMapping("/admin/movies")
  MovieResponse editMovie(@RequestBody MovieRequest body) {
    return movieService.update(body);
  }

  @DeleteMapping("/admin/movies")
  void delete(@RequestBody MovieRequest body) {
    movieService.deleteMovie(body);
  }
}
