package com.team3dat3.backend.dto.showDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import lombok.*;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowDateTimeRequest
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShowDateTimeRequest {

  private int id;

  private LocalDateTime showDate;

  private int showId;

  public void copyTo(ShowDateTime showDateTime) {
    showDateTime.setDateId(id);
    showDateTime.setShowDate(showDate);
  }

  public static ShowDateTime getShowDateTimeEntity(ShowDateTimeRequest request) {
    return ShowDateTime.builder()
        .dateId(request.getId())
        .showDate(request.getShowDate())
        .build();
  }
}
