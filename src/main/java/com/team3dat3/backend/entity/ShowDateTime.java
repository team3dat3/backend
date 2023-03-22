package com.team3dat3.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.joda.time.DateTime;

@Entity
public class ShowDateTime {

  @Id
  private DateTime showDate;

  @ManyToOne
  private Show show;
}
