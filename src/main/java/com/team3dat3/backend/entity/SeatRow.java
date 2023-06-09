package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

@Entity
public class SeatRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "seatRow")
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public SeatRow(List<Seat> seats, Theater theater) {
        this.seats = seats;
        this.theater = theater;
    }
}
