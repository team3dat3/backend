package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat request
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeatRequest {
    private Long id;
    private boolean isReserved;

    public void copy(Seat seat) {
        seat.setId(id);
        seat.setReserved(isReserved);
    }

    public Seat toSeat() {
        return new Seat(id, isReserved);
    }
}
