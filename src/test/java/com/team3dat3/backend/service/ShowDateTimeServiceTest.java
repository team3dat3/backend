package com.team3dat3.backend.service;


import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

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
        .showDate(DateTime.now())
        .build();

    showDate2 = ShowDateTime.builder()
        .showDate(DateTime.now())
        .build();

    showDateTimeRepository.save(showDate1);
    showDateTimeRepository.save(showDate2);
    showDateTimeRepository.flush();
  }
  @Test
  void findAll() {
  }

  @Test
  void find() {
  }

  @Test
  void create() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }
}