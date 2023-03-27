package com.team3dat3.backend.entity;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Reservation entity
 */

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Used to check if the user has checked in or not
    private boolean checkedIn = false;

    @ManyToOne
    private Show show;

    @ManyToOne
    private User user;

    public Reservation(int id, boolean checkedIn, Show show) {
        this.id = id;
        this.checkedIn = checkedIn;
        this.show = show;
    }

    public Reservation(User user, Show show) {
        this.user = user;
        this.show = show;
    }
}
