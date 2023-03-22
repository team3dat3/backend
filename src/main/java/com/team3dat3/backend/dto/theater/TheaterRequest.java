package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater request
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TheaterRequest {
    private Long id;
    private List<Long> seatRowIds;

    public void copy(Theater theater) {
        theater.setId(id);
        List<SeatRow> seatRows = new ArrayList<>();
        for (Long seatRowId : this.seatRowIds) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seatRows.add(seatRow);
        }
        theater.setSeatRows(seatRows);
    }

    public Theater toTheater() {
        List<SeatRow> seatRows = new ArrayList<>();
        for (Long seatRowId : this.seatRowIds) {
            SeatRow seatRow = new SeatRow();
            seatRow.setId(seatRowId);
            seatRows.add(seatRow);
        }
        return new Theater(id, seatRows);
    }
}
