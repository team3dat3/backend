package com.team3dat3.backend.api;

import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.dto.show.ShowResponse;
import com.team3dat3.backend.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

  ShowService showService;

  public ShowController(ShowService showService) {
    this.showService = showService;
  }

  @GetMapping
  List<ShowResponse> getShows(){return showService.findAll();}

  @GetMapping("/{id}")
  ShowResponse getShowById(@PathVariable int id){return showService.find(id);}

  @PostMapping()
  ShowResponse create(@RequestBody ShowRequest body){return showService.create(body);}

  @PatchMapping()
  ShowResponse update(@RequestBody ShowRequest body){return showService.update(body);}

  @DeleteMapping()
  void deleteShow(@RequestBody ShowRequest body) {
    showService.delete(body);
  }
}
