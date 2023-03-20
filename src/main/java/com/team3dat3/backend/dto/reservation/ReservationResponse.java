package com.team3dat3.backend.dto.reservation;

import com.team3dat3.backend.entity.Reservation;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    private int id;

    public ReservationResponse(Reservation reservation) {
        id = reservation.getId();
    }
}
