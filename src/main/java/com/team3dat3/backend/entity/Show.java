package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Show {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int showId;

  @ManyToOne
  private Movie movie;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "show", fetch = FetchType.LAZY)
  private List<Reservation> reservations;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "show", fetch = FetchType.LAZY)
  private List<ShowDateTime> showDates;
}
