package com.team3dat3.backend.service;


import com.team3dat3.backend.dto.showDateTime.ShowDateTimeRequest;
import com.team3dat3.backend.dto.showDateTime.ShowDateTimeResponse;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Author: Thomas S. Andersen
 * Date: 23/03/2023
 * Description: ShowDateServiceTest
 */

@DataJpaTest
class ShowDateTimeServiceTest {

  @Autowired
  ShowDateTimeRepository showDateTimeRepository;

  ShowDateTimeService showDateTimeService;

  ShowDateTime showDate1;
  ShowDateTime showDate2;


  @BeforeEach
  void beforeEach(){
    showDateTimeService = new ShowDateTimeService(showDateTimeRepository);

    showDate1 = ShowDateTime.builder()
        .showDate(LocalDateTime.now())
        .build();

    showDate2 = ShowDateTime.builder()
        .showDate(LocalDateTime.now())
        .build();

    showDateTimeRepository.save(showDate1);
    showDateTimeRepository.save(showDate2);
    showDateTimeRepository.flush();
  }
  @Test
  void findAll() {
    List<ShowDateTimeResponse> showDateTimeResponses = showDateTimeService.findAll();
    assertEquals(2, showDateTimeResponses.size());
  }

  @Test
  void find() {
    ShowDateTimeResponse findShowDate = showDateTimeService.find(showDate1.getDateId());
    assertEquals(showDate1.getDateId(), findShowDate.getDateId());
  }

  @Test
  void create() {
    ShowDateTimeRequest showDateTimeRequest3 = ShowDateTimeRequest.builder()
        .showDate(LocalDateTime.now())
        .build();

    ShowDateTimeResponse showDateTimeResponse = showDateTimeService.create(showDateTimeRequest3);
    assertEquals(showDateTimeRequest3.getShowDate(), showDateTimeResponse.getShowDate());
  }

  @Test
  void update() {
    ShowDateTime showDate4 = ShowDateTime.builder()
        .showDate(LocalDateTime.now())
        .build();

    showDateTimeRepository.save(showDate4);
    ShowDateTimeRequest show4 = ShowDateTimeRequest.builder()
        .dateId(showDate4.getDateId())
        .showDate(LocalDateTime.now())
        .build();
    showDateTimeService.update(show4);
    ShowDateTimeResponse showDateTimeResponse = showDateTimeService.find(showDate4.getDateId());
    assertEquals(showDateTimeResponse.getShowDate(), showDate4.getShowDate());
  }

  @Test
  void delete() {
    ShowDateTime showDateTime5 = ShowDateTime.builder()
        .showDate(LocalDateTime.now())
        .build();
    showDateTimeRepository.save(showDateTime5);
    ShowDateTimeRequest showDateDelete = ShowDateTimeRequest.builder()
        .dateId(showDateTime5.getDateId())
        .build();
    showDateTimeService.delete(showDateDelete);
    assertThrows(ResponseStatusException.class, () -> {
      showDateTimeService.find(showDateTime5.getDateId());});
  }
}