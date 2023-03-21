package com.team3dat3.backend.dto;

import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Show;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequest {

  private String title;
  private String director;
  private String actors;
  private int year;
  private int ageLimit;
  private String description;
  private String genre;
  private String runtime;

public static Movie getMovieEntity(MovieRequest m){
return Movie.builder().title(m.getTitle())
    .director(m.getDirector())
    .actors(m.getActors())
    .year(m.getYear())
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
    this.year = m.getYear();
    this.ageLimit = m.getAgeLimit();
    this.description = m.getDescription();
    this.genre = m.getGenre();
    this.runtime = m.getRuntime();

  }

}
