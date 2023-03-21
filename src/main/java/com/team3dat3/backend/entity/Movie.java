package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
  private int ageLimit;
  private String description;
  private String genre;
  private String runtime;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  private List<Show> shows;
}
