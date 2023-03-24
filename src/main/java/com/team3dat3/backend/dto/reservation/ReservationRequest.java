package com.team3dat3.backend.dto.reservation;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation request
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
public class ReservationRequest {
    private int id;
    private boolean checkedIn;

    private User user;

    private List<Seat> seats;
    private Show show;

    public void copyTo(Reservation reservation) {
        reservation.setId(id);
        reservation.setCheckedIn(checkedIn);
        reservation.setUser(user);
        reservation.setShow(show);
    }

    public Reservation toReservation() {
        return new Reservation(id, checkedIn, user, seats, show);
    }
}
