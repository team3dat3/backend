package com.team3dat3.backend.dto.reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation response
 */

import com.team3dat3.backend.entity.Reservation;

import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.User;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    private int id;

    private boolean checkedIn;

    private Show show;
    private User user;

    public ReservationResponse(Reservation reservation) {
        id = reservation.getId();
        checkedIn = reservation.isCheckedIn();
        show = reservation.getShow();
        user = reservation.getUser();
    }
}
