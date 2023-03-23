package com.team3dat3.backend.api.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.api.v1.ShowDateTimeController;
import com.team3dat3.backend.dto.showDateTime.ShowDateTimeRequest;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.service.ShowDateTimeService;
import com.team3dat3.backend.service.ShowService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowDateTime controller test
 */

@DataJpaTest
class ShowDateTimeControllerTest {

  @Autowired
  ShowDateTimeRepository showDateTimeRepository;

  ShowDateTimeService showDateTimeService;
  ShowDateTimeController showDateTimeController;

  @Autowired
  ShowRepository showRepository;

  ShowService showService;

  private ShowDateTime showDate1;
  private ShowDateTime showDate2;

  private static final LocalDateTime FIXED_DATE_TIME = LocalDateTime.of(2023, 3, 22, 12, 0);
  private Show show1;
  private Show show2;

  MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    showDateTimeService = new ShowDateTimeService(showDateTimeRepository);
    showService = new ShowService(showRepository);
    showDateTimeController = new ShowDateTimeController(showDateTimeService);
    mockMvc = MockMvcBuilders.standaloneSetup(showDateTimeController).build();

    show1 = Show.builder()
        .price(120)
        .build();

    show2 = Show.builder()
        .price(100)
        .build();

    showRepository.save(show1);
    showRepository.save(show2);
    showRepository.flush();


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
  void getShowsDates() throws Exception{
    mockMvc.perform(get("/v1/anonymous/showdates"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(Matchers.not(0))));
  }

  @Test
  void getShowDatesById() throws Exception{
    mockMvc.perform(get("/v1/anonymous/showdates/" + showDate1.getDateId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.dateId", is(Matchers.not(0))));
  }

  @Test
  void create() throws Exception{
    ShowDateTimeRequest showDate3 = ShowDateTimeRequest.builder()
        .build();
    mockMvc.perform(post("/v1/admin/showdates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(showDate3)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.dateId", is(Matchers.not(0))));
  }

  @Test
  void update() throws Exception{
/*
 ShowDateTime showDate5 = ShowDateTime.builder()
        .show(show1)
        .build();

    showDateTimeRepository.save(showDate5);

    ShowDateTimeRequest showDate6 = ShowDateTimeRequest.builder()
        .show(show2)
        .build();

    mockMvc.perform(patch("/v1/admin/showdates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(showDate6)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.dateId", is(showDate5.getDateId())))
        .andExpect(jsonPath("$.show.showId", is(showDate6.getShow().getShowId())));*/
  }

  @Test
  void deleteShow() throws Exception{
    ShowDateTimeRequest deleteRequest = new ShowDateTimeRequest();
    deleteRequest.setDateId(showDate1.getDateId());
    mockMvc.perform(delete("/v1/admin/showdates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(deleteRequest)))
        .andExpect(status().isOk());
  }
}