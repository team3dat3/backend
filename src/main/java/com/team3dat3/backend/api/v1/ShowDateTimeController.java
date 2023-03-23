package com.team3dat3.backend.api.v1;


import com.team3dat3.backend.dto.showDateTime.ShowDateTimeRequest;
import com.team3dat3.backend.dto.showDateTime.ShowDateTimeResponse;
import com.team3dat3.backend.service.ShowDateTimeService;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
 * Author: Thomas S. Andersen
 * Date: 22/03/2023
 * Description: ShowDateTimeController
 */

@RestController
@RequestMapping("/api/v1/showdates")
public class ShowDateTimeController {

  ShowDateTimeService showDateTimeService;

  public ShowDateTimeController(ShowDateTimeService showDateTimeService) {
    this.showDateTimeService = showDateTimeService;
  }

  @GetMapping
  List<ShowDateTimeResponse> getShowsDates(){return showDateTimeService.findAll();}

  @GetMapping("/{id}")
  ShowDateTimeResponse getShowDatesById(@PathVariable int id){return showDateTimeService.find(id);}

  @PostMapping()
  ShowDateTimeResponse create(@RequestBody ShowDateTimeRequest body){return showDateTimeService.create(body);}

  @PatchMapping()
  ShowDateTimeResponse update(@RequestBody ShowDateTimeRequest body){return showDateTimeService.update(body);}

  @DeleteMapping()
  void deleteShow(@RequestBody ShowDateTimeRequest body) {
    showDateTimeService.delete(body);
  }
}