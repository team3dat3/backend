package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Seat entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isReserved;

    @ManyToOne
    @JoinColumn(name = "seat_row_id")
    private SeatRow seatRow;
}