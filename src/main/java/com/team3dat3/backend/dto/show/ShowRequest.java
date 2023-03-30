package com.team3dat3.backend.dto.show;

import com.team3dat3.backend.entity.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowRequest
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShowRequest {


  private int id;

  private String movieTitle;

  private List<Integer> showDateTimesIds;

  private List<LocalDateTime> showDateTimes;

  private double price;

  private Long theaterId;

  public void copyTo(Show show) {
    show.setId(id);
    show.setPrice(price);
  }

  public static Show getShowEntity(ShowRequest request) {
    return Show.builder()
        .id(request.getId())
        .price(request.getPrice())
        .build();
  }

  public Show toShow() {
    Show show = new Show();
    copyTo(show);
    return show;
  }
}
