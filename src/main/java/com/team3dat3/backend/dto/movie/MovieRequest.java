package com.team3dat3.backend.dto.movie;

import com.team3dat3.backend.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 21/03/2023
 * Description: MovieRequest
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

  private String title;
  private String director;
  private String actors;
  private int prodYear;
  private String rated;
  private String description;
  private List <String> genre;
  private String runtime;
  private String poster;

public static Movie getMovieEntity(MovieRequest m){
return Movie.builder().title(m.getTitle())
    .director(m.getDirector())
    .actors(m.getActors())
    .prodYear(m.getProdYear())
    .rated(m.getRated())
    .description(m.getDescription())
    .genre(m.getGenre())
    .runtime(m.getRuntime())
    .poster(m.getPoster())
    .build();

}

  public void copyTo(Movie movie) {
    movie.setTitle(title);
    movie.setDirector(director);
    movie.setActors(actors);
    movie.setProdYear(prodYear);
    movie.setRated(rated);
    movie.setDescription(description);
    movie.setGenre(genre);
    movie.setRuntime(runtime);
    movie.setPoster(poster);
  }

  public MovieRequest(Movie m) {
    this.title = m.getTitle();
    this.director = m.getDirector();
    this.actors = m.getActors();
    this.prodYear = m.getProdYear();
    this.rated = m.getRated();
    this.description = m.getDescription();
    this.genre = m.getGenre();
    this.runtime = m.getRuntime();
    this.poster = m.getPoster();
  }

}
