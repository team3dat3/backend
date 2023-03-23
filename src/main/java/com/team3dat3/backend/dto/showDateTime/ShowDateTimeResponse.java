package com.team3dat3.backend.dto.showDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowDateTimeResponse {

  private int dateId;
  @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime showDate;

  private Show show;

  public ShowDateTimeResponse(ShowDateTime showDateTime) {
    this.dateId = showDateTime.getDateId();
    this.showDate = showDateTime.getShowDate();
    this.show = showDateTime.getShow();

  }
}
