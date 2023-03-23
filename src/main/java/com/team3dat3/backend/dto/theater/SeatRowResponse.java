package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.SeatRow;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SeatRowResponse {
    private Long id;
    private List<SeatResponse> seats;

    public SeatRowResponse(SeatRow seatRow) {
        this.id = seatRow.getId();
        if (seatRow.getSeats() != null) {
            List<SeatResponse> seatList = seatRow.getSeats()
                    .stream()
                    .map(SeatResponse::new)
                    .collect(Collectors.toList());
            this.seats = seatList;
        }
    }
}
