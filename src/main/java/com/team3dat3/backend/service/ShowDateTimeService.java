package com.team3dat3.backend.service;


import com.team3dat3.backend.dto.showDateTime.ShowDateTimeRequest;
import com.team3dat3.backend.dto.showDateTime.ShowDateTimeResponse;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowDateTimeService {

  private ShowDateTimeRepository showDateTimeRepository;

  public ShowDateTimeService(ShowDateTimeRepository showDateTimeRepository) {
    this.showDateTimeRepository = showDateTimeRepository;
  }

  public List<ShowDateTimeResponse> findAll() {
    List<ShowDateTime> allShowDates = showDateTimeRepository.findAll();
    List<ShowDateTimeResponse> showDateTimeResponses = allShowDates.stream().map(s->new ShowDateTimeResponse(s)).collect(Collectors.toList());
    return showDateTimeResponses;
  }


  public ShowDateTimeResponse find(DateTime datetime) {
    ShowDateTime foundShowDateTime = showDateTimeRepository.findById(datetime)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show date with this id unknown"));
    return new ShowDateTimeResponse(foundShowDateTime);
  }

  public ShowDateTimeResponse create(ShowDateTimeRequest request) {
    ShowDateTime showDateTime = ShowDateTimeRequest.getShowDateTimeEntity(request);
    showDateTimeRepository.save(showDateTime);
    return new ShowDateTimeResponse(showDateTime);
  }

  public ShowDateTimeResponse update(ShowDateTimeRequest request) {
    ShowDateTime foundShowDate = showDateTimeRepository.findById(request.getShowDate())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show date with this id unknown"));
    request.copyTo(foundShowDate);
    return new ShowDateTimeResponse(showDateTimeRepository.save(foundShowDate));
  }

  public void delete(ShowDateTimeRequest request) {
    ShowDateTime showDateTime = showDateTimeRepository.findById(request.getShowDate())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    showDateTimeRepository.delete(showDateTime);
  }
}
