package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "discount", length = 50, nullable = false)
    private double discount;
    @ManyToOne
    private User user;
    @Column(name = "cost", length = 50, nullable = false)
    private double cost;

    private boolean used = false;

    public Coupon(String name, double discount, double cost){
        this.name = name;
        this.discount = discount;
        this.cost = cost;
    }
}
