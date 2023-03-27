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
public class SeatRowResponse {
    private Long id;
    private List<Seat> seats;

    private Theater theater;

    public SeatRowResponse(SeatRow seatRow) {
        this.id = seatRow.getId();
        this.seats = seatRow.getSeats();
        this.theater = seatRow.getTheater();
    }
}
