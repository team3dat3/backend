package com.team3dat3.backend.dto.showDateTime;

import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowDateTimeResponse {

  private DateTime showDate;

  private Show show;

  public ShowDateTimeResponse(ShowDateTime showDateTime) {
    this.showDate = showDateTime.getShowDate();
    this.show = showDateTime.getShow();

  }
}
