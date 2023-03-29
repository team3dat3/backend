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
    private List<Long> seatIds;
    private Long theaterId;

    public void copy(SeatRow seatRow) {
        seatRow.setId(id);
    }

    public SeatRow toSeatRow() {
        SeatRow seatRow = new SeatRow();
        copy(seatRow);
        return seatRow;
    }
}
