package com.team3dat3.backend.dto.showDateTime;

import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowDateTimeRequest {

  private int dateId;
  private DateTime showDate;

  private Show show;

  public void copyTo(ShowDateTime showDateTime) {
    showDateTime.setDateId(dateId);
    showDateTime.setShowDate(showDate);
    showDateTime.setShow(show);
  }

  public static ShowDateTime getShowDateTimeEntity(ShowDateTimeRequest request) {
    return ShowDateTime.builder()
        .dateId(request.getDateId())
        .showDate(request.getShowDate())
        .show(request.getShow())
        .build();
  }
}
