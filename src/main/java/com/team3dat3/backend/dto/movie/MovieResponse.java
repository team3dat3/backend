package com.team3dat3.backend.dto.movie;

import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 21/03/2023
 * Description: MovieResponse
 */

@Getter
@Setter
@NoArgsConstructor
public class MovieResponse {

  private String title;
  private String director;
  private String actors;
  private int prodYear;
  private int ageLimit;
  private String description;
  private String genre;
  private String runtime;
  private List<Show> shows;

  public MovieResponse(Movie m) {
    this.title = m.getTitle();
    this.director = m.getDirector();
    this.actors = m.getActors();
    this.prodYear = m.getProdYear();
    this.ageLimit = m.getAgeLimit();
    this.description = m.getDescription();
    this.genre = m.getGenre();
    this.runtime = m.getRuntime();
    this.shows = m.getShows();
  }
}
