package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import lombok.*;

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
    private boolean isReserved;
    private Long seatRowId;

    public void copy(Seat seat) {
        seat.setId(id);
        seat.setReserved(isReserved);
        if (seatRowId != null) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seat.setSeatRow(seatRow);
        }
    }

    public Seat toSeat() {
        Seat seat = new Seat(id, isReserved, null); // updated constructor call
        if (seatRowId != null) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seat.setSeatRow(seatRow);
        }
        return seat;
    }
}
