package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatRowRequest {
    private Long id;

    private List<Seat> seats;

    private Theater theater;

    public void copy(SeatRow seatRow) {
        seatRow.setId(id);
        seatRow.setSeats(seats);
        seatRow.setTheater(theater);
    }

    public SeatRow toSeatRow() {
        return new SeatRow(id, seats, theater);
    }
}
