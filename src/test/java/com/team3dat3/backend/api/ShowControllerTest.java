package com.team3dat3.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.reservation.ReservationRequest;
import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.repository.MovieRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.service.MovieService;
import com.team3dat3.backend.service.ShowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DataJpaTest
class ShowControllerTest {

  @Autowired
  ShowRepository showRepository;

  ShowService showService;
  ShowController showController;

  private Show show1;
  private Show show2;

  MockMvc mockMvc;

  @BeforeEach
  void beforeEach() {
    showService = new ShowService(showRepository);
    showController = new ShowController(showService);
    mockMvc = MockMvcBuilders.standaloneSetup(showController).build();

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
  void getShows() throws Exception{
    mockMvc.perform(get("/api/shows"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  void getShowById() throws Exception{
    mockMvc.perform(get("/api/shows/" + show1.getShowId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.showId", is(show1.getShowId())));
  }

  @Test
  void create() throws Exception{
    ShowRequest show3 = ShowRequest.builder()
        .price(80)
        .build();
    mockMvc.perform(post("/api/shows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(show3)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.showId", is(3)));
  }

  @Test
  void update() throws Exception{
    Show show3 = Show.builder()
        .price(100)
        .build();

    showRepository.save(show3);
    ShowRequest show4 = ShowRequest.builder()
        .showId(show3.getShowId())
        .price(20)
        .build();

    mockMvc.perform(patch("/api/shows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(show4)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.showId", is(show3.getShowId())))
        .andExpect(jsonPath("$.price", is(20.0)));
  }

  @Test
  void deleteShow() throws Exception{
    ShowRequest deleteRequest = new ShowRequest();
    deleteRequest.setShowId(show1.getShowId());
    mockMvc.perform(delete("/api/shows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(deleteRequest)))
        .andExpect(status().isOk());
  }
}