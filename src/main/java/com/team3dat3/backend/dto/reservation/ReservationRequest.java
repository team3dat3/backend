package com.team3dat3.backend.dto.reservation;

import com.team3dat3.backend.entity.Reservation;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private int id;

    public void copyTo(Reservation reservation) {
        reservation.setId(id);
    }

    public Reservation toReservation() {
        return new Reservation(id);
    }
}
