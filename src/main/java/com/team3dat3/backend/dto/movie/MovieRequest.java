package com.team3dat3.backend.dto.movie;

import com.team3dat3.backend.entity.Movie;
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
@NoArgsConstructor
public class MovieRequest {

  private String title;
  private String director;
  private String actors;
  private int prodYear;
  private int ageLimit;
  private String description;
  private String genre;
  private String runtime;

public static Movie getMovieEntity(MovieRequest m){
return Movie.builder().title(m.getTitle())
    .director(m.getDirector())
    .actors(m.getActors())
    .prodYear(m.getProdYear())
    .ageLimit(m.getAgeLimit())
    .description(m.getDescription())
    .genre(m.getGenre())
    .runtime(m.getRuntime())
    .build();

}

  public void copyTo(Movie movie) {
    movie.setTitle(title);
    movie.setDirector(director);
    movie.setActors(actors);
    movie.setProdYear(prodYear);
    movie.setAgeLimit(ageLimit);
    movie.setDescription(description);
    movie.setGenre(genre);
    movie.setRuntime(runtime);
  }

  public MovieRequest(Movie m) {
    this.title = m.getTitle();
    this.director = m.getDirector();
    this.actors = m.getActors();
    this.prodYear = m.getProdYear();
    this.ageLimit = m.getAgeLimit();
    this.description = m.getDescription();
    this.genre = m.getGenre();
    this.runtime = m.getRuntime();

  }

}
