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
    private Long seatRowId;

    public void copy(Seat seat) {
        seat.setId(id);
    }

    public Seat toSeat() {
        Seat seat = new Seat();
        copy(seat);
        return seat;
    }
}
