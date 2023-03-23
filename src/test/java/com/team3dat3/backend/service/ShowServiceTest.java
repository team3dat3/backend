package com.team3dat3.backend.service;


import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.dto.show.ShowResponse;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowServiceTest
 */

@DataJpaTest
class ShowServiceTest {


  @Autowired
  ShowRepository showRepository;

  ShowService showService;

  Show show1;
  Show show2;

  @BeforeEach
  void beforeEach(){
    showService = new ShowService(showRepository);

    show1 = Show.builder()
            .price(120)
            .build();

    show2 = Show.builder()
        .price(100)
        .build();

    showRepository.save(show1);
    showRepository.save(show2);
    showRepository.flush();
  }

  @Test
  void findAll() {
    List<ShowResponse> showResponses = showService.findAll();
    assertEquals(2, showResponses.size());
  }

  @Test
  void find() {
    ShowResponse findShow = showService.find(show1.getId());
    assertEquals(show1.getId(), findShow.getId());
  }

  @Test
  void create() {
    ShowRequest show3 = ShowRequest.builder()
        .price(80)
        .build();

    ShowResponse showResponse = showService.create(show3);
    assertEquals(80, showResponse.getPrice());
  }

  @Test
  void update() {
    Show show3 = Show.builder()
        .price(100)
        .build();

    showRepository.save(show3);
    ShowRequest show4 = ShowRequest.builder()
        .id(show3.getId())
        .price(20)
        .build();
    showService.update(show4);
    ShowResponse showResponse = showService.find(show3.getId());
    assertEquals(showResponse.getPrice(), show4.getPrice());
  }

  @Test
  void delete() {
    Show show5 = Show.builder()
        .price(20)
        .build();
    showRepository.save(show5);
    ShowRequest showDelete = ShowRequest.builder()
        .id(show5.getId())
        .price(20)
        .build();
    showService.delete(showDelete);
    assertThrows(ResponseStatusException.class, () -> {
      showService.find(show5.getId());});
  }
}