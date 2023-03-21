package com.team3dat3.backend.dto.reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation request
 */

import com.team3dat3.backend.entity.Reservation;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private int id;
    private boolean checkedIn;

    public void copyTo(Reservation reservation) {
        reservation.setId(id);
        reservation.setCheckedIn(checkedIn);
    }

    public Reservation toReservation() {
        return new Reservation(id, checkedIn);
    }
}
