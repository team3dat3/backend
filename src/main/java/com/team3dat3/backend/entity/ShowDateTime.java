package com.team3dat3.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

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
