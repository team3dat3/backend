package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat request
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatRequest {
    private Long id;
    private List<Integer> reservationIds;
    private Long seatRowId;

    public void copy(Seat seat) {
        seat.setId(id);
        if (reservationIds != null) {
            List<Reservation> reservationList = reservationIds.stream().map(reservationId -> {
                Reservation reservation = new Reservation();
                reservation.setId(reservationId);
                return reservation;
            }).collect(Collectors.toList());
            seat.setReservations(reservationList);
        }
        if (seatRowId != null) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seat.setSeatRow(seatRow);
        }
    }

    public Seat toSeat() {
        Seat seat = new Seat(id, null, null);
        if (reservationIds != null) {
            List<Reservation> reservationList = reservationIds.stream().map(reservationId -> {
                Reservation reservation = new Reservation();
                reservation.setId(reservationId);
                return reservation;
            }).collect(Collectors.toList());
            seat.setReservations(reservationList);
        }
        if (seatRowId != null) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seat.setSeatRow(seatRow);
        }
        return seat;
    }
}
