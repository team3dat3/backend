package com.team3dat3.backend.dto.theater;

import com.team3dat3.backend.entity.Seat;
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
 * Description: Theater request
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TheaterRequest {
    private Long id;
    private String name;
    private List<Long> seatRowIds;

    public void copy(Theater theater) {
        theater.setId(id);
        theater.setName(name);
    }

    public Theater toTheater() {
        return new Theater(id, name);
    }
}
