package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.show.ShowRequest;
import com.team3dat3.backend.dto.show.ShowResponse;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.repository.ShowRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

  private ShowRepository showRepository;

  public ShowService(ShowRepository showRepository) {
    this.showRepository = showRepository;
  }

  public List<ShowResponse> findAll() {
    List<Show> allShows = showRepository.findAll();
    List<ShowResponse> showResponses = allShows.stream().map(s->new ShowResponse(s)).collect(Collectors.toList());
    return showResponses;
  }


  public ShowResponse find(int id) {
    Show foundShow = showRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show with this id unknown"));
    return new ShowResponse(foundShow);
  }

  public ShowResponse create(ShowRequest request) {
    Show show = ShowRequest.getShowEntity(request);
    showRepository.save(show);
    return new ShowResponse(show);
  }

  public ShowResponse update(ShowRequest request) {
    Show foundShow = showRepository.findById(request.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show with this id unknown"));
    request.copyTo(foundShow);
    return new ShowResponse(showRepository.save(foundShow));
  }

  public void delete(ShowRequest request) {
    Show show = showRepository.findById(request.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    showRepository.delete(show);
  }
}
