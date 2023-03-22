package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import lombok.*;

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
    private boolean isReserved;

    public SeatResponse(Seat seat) {
        id = seat.getId();
        isReserved = seat.isReserved();
    }
}
