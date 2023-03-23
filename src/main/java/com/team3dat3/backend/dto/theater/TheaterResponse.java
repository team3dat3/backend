package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.Theater;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TheaterResponse {
    private Long id;
    private List<SeatRow> seatRows;

    @OneToMany
    private List<Show> shows;
    public TheaterResponse(Theater theater) {
        this.id = theater.getId();
        this.seatRows = theater.getSeatRows();
        this.shows = theater.getShows();
    }
}
