package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * Author: Mads Kristian Pedersen
 * Date: 21/03/2023
 * Description: Theater entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "theater")
    private List<SeatRow> seatRows;

    public Theater(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
