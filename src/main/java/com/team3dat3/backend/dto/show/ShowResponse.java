package com.team3dat3.backend.dto.show;

import com.team3dat3.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowResponse
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowResponse {

  private int id;

  private Movie movie;

  private List<Reservation> reservations;

  private List<ShowDateTime> showDates;

  private double price;

  private Theater theater;

  public ShowResponse(Show show) {
    this.id = show.getId();
    this.movie = show.getMovie();
    this.reservations = show.getReservations();
    this.showDates = show.getShowDates();
    this.price = show.getPrice();
    this.theater = show.getTheater();
  }
}
