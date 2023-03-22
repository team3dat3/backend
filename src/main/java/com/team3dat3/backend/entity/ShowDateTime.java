package com.team3dat3.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;

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

  private DateTime showDate;

  @ManyToOne
  private Show show;
}
