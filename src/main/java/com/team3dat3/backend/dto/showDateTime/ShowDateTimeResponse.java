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

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowDateTimeResponse
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowDateTimeResponse {

  private int id;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime showDate;

  private int showId;

  public ShowDateTimeResponse(ShowDateTime showDateTime) {
    this.id = showDateTime.getDateId();
    this.showDate = showDateTime.getShowDate();
    this.showId = showDateTime.getShow() != null ? showDateTime.getShow().getId() : 0;
  }
}
