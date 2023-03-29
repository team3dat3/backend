package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 21/03/2023
 * Description: Movie entity
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie {

  @Id
  private String title;
  private String director;
  private String actors;
  private int prodYear;
  private String rated;
  private String description;
  @ElementCollection
  private List <String> genre;
  private String runtime;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
  private List<Show> shows;
}
