package com.team3dat3.backend.dto.show;

import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowResponse {

  private int showId;

  private Movie movie;

  private List<Reservation> reservations;

  private List<ShowDateTime> showDates;

  public ShowResponse(Show show) {
    this.showId = show.getShowId();
    this.movie = show.getMovie();
    this.reservations = show.getReservations();
    this.showDates = show.getShowDates();
  }
}
