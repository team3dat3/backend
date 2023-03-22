package com.team3dat3.backend.dto.movie;

import com.team3dat3.backend.entity.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
