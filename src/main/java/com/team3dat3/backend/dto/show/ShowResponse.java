package com.team3dat3.backend.dto.show;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3dat3.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  private String movieTitle;

  private List<Integer> showDateTimesIds;

  // Format: yyyy-MM-dd HH:mm:ss
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private List<LocalDateTime> showDateTimes;

  private double price;

  private Long theaterId;
  private String theaterName;

  public ShowResponse(Show show) {
    this.id = show.getId();
    this.movieTitle = show.getMovie() != null ? show.getMovie().getTitle() : null;
    this.theaterId = show.getTheater() != null ? show.getTheater().getId() : 0;
    this.theaterName = show.getTheater() != null ? show.getTheater().getName() : null;
    this.price = show.getPrice();
    this.showDateTimesIds = show.getShowDates() != null ? show.getShowDates().stream().map(d->d.getDateId()).collect(Collectors.toList()) : new ArrayList<>();
    this.showDateTimes = show.getShowDates() != null ? show.getShowDates().stream().map(d->d.getShowDate()).collect(Collectors.toList()) : new ArrayList<>();
  }
}
