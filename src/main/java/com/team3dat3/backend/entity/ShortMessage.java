package com.team3dat3.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-21
 * Description: Short message
 */

import jakarta.persistence.*;
import lombok.*;
 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ShortMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String message;
    private String sender;
    private String receiver;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public ShortMessage(String message, String sender, String receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }
}
