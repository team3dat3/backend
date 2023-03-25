package com.team3dat3.backend.api.v1;

import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.dto.show.ShowResponse;
import com.team3dat3.backend.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowController
 */

@RestController
@RequestMapping("/v1")
public class ShowController {

  ShowService showService;

  public ShowController(ShowService showService) {
    this.showService = showService;
  }

  @GetMapping("/anonymous/shows")
  List<ShowResponse> getShows(){return showService.findAll();}

  @GetMapping("/anonymous/shows/{id}")
  ShowResponse getShowById(@PathVariable int id){return showService.find(id);}

  @PostMapping("/admin/shows")
  ShowResponse create(@RequestBody ShowRequest body){return showService.create(body);}

  @PatchMapping("/admin/shows")
  ShowResponse update(@RequestBody ShowRequest body){return showService.update(body);}

  @DeleteMapping("/admin/shows")
  void deleteShow(@RequestBody ShowRequest body) {
    showService.delete(body);
  }
}
