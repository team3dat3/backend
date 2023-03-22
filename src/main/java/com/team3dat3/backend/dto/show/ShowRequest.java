package com.team3dat3.backend.dto.show;

import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import lombok.*;

import java.util.List;


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
  public void copyTo(Show show) {
    show.setShowId(showId);
    show.setMovie(movie);
    show.setReservations(reservations);
    show.setShowDates(showDates);
  }

  public static Show getShowEntity(ShowRequest request) {
    return Show.builder()
        .showId(request.getShowId())
        .movie(request.getMovie())
        .reservations(request.getReservations())
        .showDates(request.getShowDates())
        .build();
  }
}
