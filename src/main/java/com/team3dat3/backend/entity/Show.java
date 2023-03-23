package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 23/03/2023
 * Description: Show entity
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Movie movie;

  @OneToMany//(cascade = CascadeType.ALL, mappedBy = "show", fetch = FetchType.LAZY)
  private List<Reservation> reservations;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "show", fetch = FetchType.LAZY)
  private List<ShowDateTime> showDates;

  private double price;

  @ManyToOne
  private Theater theater;
}
