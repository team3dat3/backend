package com.team3dat3.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
  private DateTime showDate;

  @ManyToOne
  private Show show;
}
