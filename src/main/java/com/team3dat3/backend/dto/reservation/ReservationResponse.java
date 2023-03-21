package com.team3dat3.backend.dto.reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation response
 */

import com.team3dat3.backend.entity.Reservation;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    private int id;

    private boolean checkedIn;

    public ReservationResponse(Reservation reservation) {
        id = reservation.getId();
        checkedIn = reservation.isCheckedIn();
    }
}
