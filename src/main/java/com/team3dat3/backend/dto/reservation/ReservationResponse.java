package com.team3dat3.backend.dto.reservation;

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

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    private int id;

    private boolean checkedIn;

    private User user;
    private Show show;

    public ReservationResponse(Reservation reservation) {
        id = reservation.getId();
        checkedIn = reservation.isCheckedIn();
        seats = reservation.getSeats();
        show = reservation.getShow();
        user = reservation.getUser();
    }
}
