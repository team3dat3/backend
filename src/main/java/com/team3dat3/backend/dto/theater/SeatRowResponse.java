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
    private List<Long> seatIds;
    private Long theaterId;

    public SeatRowResponse(SeatRow seatRow) {
        this.id = seatRow.getId();
        this.seatIds = seatRow.getSeats().stream().map(Seat::getId).toList();
        this.theaterId = seatRow.getTheater().getId();
    }
}
