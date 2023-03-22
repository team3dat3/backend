package com.team3dat3.backend.dto.showDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import lombok.*;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShowDateTimeRequest {

  private int dateId;

  @JsonFormat(pattern = "dd-mm-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime showDate;

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
