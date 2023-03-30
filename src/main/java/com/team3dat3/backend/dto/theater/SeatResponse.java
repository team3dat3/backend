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
 * Description: Seat response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatResponse {
    private Long id;
    private Long seatRowId;

    public SeatResponse(Seat seat) {
        id = seat.getId();
        seatRowId = seat.getSeatRow() != null ? seat.getSeatRow().getId() : 0;
    }
}
