package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowDateTime entity
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowDateTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int dateId;

  private LocalDateTime showDate;

  @ManyToOne
  private Show show;
}
