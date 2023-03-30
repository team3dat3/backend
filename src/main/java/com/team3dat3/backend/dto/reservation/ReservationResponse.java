package com.team3dat3.backend.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation response
 */

import com.team3dat3.backend.entity.Reservation;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.User;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    private int id;

    private boolean checkedIn;

    private String username;

    private int showId;
    private String showMovieTitle;

    private List<Long> seatIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime showDateTime;

    private String theaterName;

    public ReservationResponse(Reservation reservation) {
        id = reservation.getId();
        checkedIn = reservation.isCheckedIn();
        username = reservation.getUser() != null ? reservation.getUser().getUsername() : "";
        showId = reservation.getShow() != null ? reservation.getShow().getId() : 0;
        showMovieTitle = (reservation.getShow() != null && reservation.getShow().getMovie() != null) ? reservation.getShow().getMovie().getTitle() : "";
        seatIds = reservation.getSeats() != null ? reservation.getSeats().stream().map(Seat::getId).collect(Collectors.toList()) : null;
        showDateTime = reservation.getShowDateTime() != null ? reservation.getShowDateTime().getShowDate() : null;
        theaterName = (reservation.getShow() != null && reservation.getShow().getTheater() != null) ? reservation.getShow().getTheater().getName() : "";
    }
}
