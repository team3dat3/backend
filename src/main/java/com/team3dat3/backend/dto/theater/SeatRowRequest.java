package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatRowRequest {
    private Long id;
    private List<Seat> seats;
    public void copy(SeatRow seatRow) {
        seatRow.setId(id);
        if (seats != null) {
            List<Seat> seatList = seats.stream().map(seatRequest -> {
                Seat seat = new Seat();
                return seat;
            }).collect(Collectors.toList());
            seatRow.setSeats(seatList);
        }
    }

    public SeatRow toSeatRow() {
        SeatRow seatRow = new SeatRow();
        seatRow.setId(id);
        if (seats != null) {
            List<Seat> seatList = seats.stream().map(seatRequest -> {
                Seat seat = new Seat();
                return seat;
            }).collect(Collectors.toList());
            seatRow.setSeats(seatList);
        }
        return seatRow;
    }
}
