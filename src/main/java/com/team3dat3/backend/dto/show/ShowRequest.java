package com.team3dat3.backend.dto.show;

import com.team3dat3.backend.entity.*;
import lombok.*;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowRequest
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShowRequest {


  private int showId;

  private Movie movie;

  private List<Reservation> reservations;

  private List<ShowDateTime> showDates;

  private double price;

  private Theater theater;

  public void copyTo(Show show) {
    show.setShowId(showId);
    show.setMovie(movie);
    show.setReservations(reservations);
    show.setShowDates(showDates);
    show.setPrice(price);
    show.setTheater(theater);
  }

  public static Show getShowEntity(ShowRequest request) {
    return Show.builder()
        .showId(request.getShowId())
        .movie(request.getMovie())
        .reservations(request.getReservations())
        .showDates(request.getShowDates())
        .price(request.getPrice())
        .theater(request.getTheater())
        .build();
  }
}
