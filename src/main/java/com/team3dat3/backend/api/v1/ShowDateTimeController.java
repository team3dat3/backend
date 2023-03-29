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
@RequestMapping("/v1")
public class ShowDateTimeController {

  ShowDateTimeService showDateTimeService;

  public ShowDateTimeController(ShowDateTimeService showDateTimeService) {
    this.showDateTimeService = showDateTimeService;
  }

  @GetMapping("/anonymous/showdates")
  List<ShowDateTimeResponse> getShowsDates(){return showDateTimeService.findAll();}

  @GetMapping("/anonymous/showdates/{id}")
  ShowDateTimeResponse getShowDatesById(@PathVariable int id){return showDateTimeService.find(id);}

  @PostMapping("/admin/showdates")
  ShowDateTimeResponse create(@RequestBody ShowDateTimeRequest body){return showDateTimeService.create(body);}

  @PutMapping("/admin/showdates")
  ShowDateTimeResponse update(@RequestBody ShowDateTimeRequest body){return showDateTimeService.update(body);}

  @DeleteMapping("/admin/showdates")
  void deleteShow(@RequestBody ShowDateTimeRequest body) {
    showDateTimeService.delete(body);
  }
}
