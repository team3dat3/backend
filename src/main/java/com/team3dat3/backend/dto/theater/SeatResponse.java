package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Seat;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatResponse {
    private Long id;
    private List<Integer> reservationsIds;
    private Long seatRowId;

    public SeatResponse(Seat seat) {
        id = seat.getId();
        if (seat.getReservations() != null) {
            List<Integer> reservationIds = seat.getReservations()
                    .stream()
                    .map(Reservation::getId)
                    .collect(Collectors.toList());
            this.reservationsIds = reservationIds;
        }
        if (seat.getSeatRow() != null) {
            seatRowId = seat.getSeatRow().getId();
        }
    }
}
