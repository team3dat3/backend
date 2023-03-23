package com.team3dat3.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.service.ShowService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: Show controller test
 */
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
        .andExpect(jsonPath("$", is(Matchers.not(0))));
  }

  @Test
  void getShowById() throws Exception{
    mockMvc.perform(get("/api/shows/" + show1.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(Matchers.not(0))));
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
        .andExpect(jsonPath("$.id", is(Matchers.not(0))));
  }

  @Test
  void update() throws Exception{
    Show show3 = Show.builder()
        .price(100)
        .build();

    showRepository.save(show3);
    ShowRequest show4 = ShowRequest.builder()
        .id(show3.getId())
        .price(20)
        .build();

    mockMvc.perform(patch("/api/shows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(show4)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(show3.getId())))
        .andExpect(jsonPath("$.price", is(20.0)));
  }

  @Test
  void deleteShow() throws Exception{
    ShowRequest deleteRequest = new ShowRequest();
    deleteRequest.setId(show1.getId());
    mockMvc.perform(delete("/api/shows")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(deleteRequest)))
        .andExpect(status().isOk());
  }
}