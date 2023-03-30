package com.team3dat3.backend.service;

import com.team3dat3.backend.dto.theater.SeatRowRequest;
import com.team3dat3.backend.dto.theater.SeatRowResponse;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.SeatRowRepository;
import com.team3dat3.backend.repository.TheaterRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SeatRowService {

  private final SeatRowRepository seatRowRepository;
  private final SeatRepository seatRepository;
  private final TheaterRepository theaterRepository;

  public SeatRowService(
    SeatRowRepository seatRowRepository,
    SeatRepository seatRepository,
    TheaterRepository theaterRepository
  ) {
    this.seatRowRepository = seatRowRepository;
    this.seatRepository = seatRepository;
    this.theaterRepository = theaterRepository;
  }

  public List<SeatRowResponse> getAll() {
    return seatRowRepository
      .findAll()
      .stream()
      .map(SeatRowResponse::new)
      .collect(Collectors.toList());
  }

  public SeatRowResponse get(Long id) {
    SeatRow seatRow = seatRowRepository
      .findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return new SeatRowResponse(seatRow);
  }

  public SeatRowResponse create(SeatRowRequest seatRowRequest) {
    SeatRow seatRow = seatRowRequest.toSeatRow();
    List<Seat> seats = findSeats(seatRowRequest);
    seatRow.setSeats(seats);
    if (seatRowRequest.getTheaterId() == 0) seatRow.setTheater(
      null
    ); else seatRow.setTheater(findTheater(seatRowRequest));
    seatRow = seatRowRepository.save(seatRow);
    setupSeats(seats, seatRow);
    return new SeatRowResponse(seatRow);
  }

  public SeatRowResponse update(SeatRowRequest seatRowRequest) {
    SeatRow seatRow = seatRowRepository
      .findById(seatRowRequest.getId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    seatRowRequest.copy(seatRow);
    
    clearSeats(seatRow);
    List<Seat> seats = findSeats(seatRowRequest);
    updateSeats(seatRow, seats);
    seatRow.setSeats(seats);

    if (seatRowRequest.getTheaterId() == 0) seatRow.setTheater(
      null
    ); else seatRow.setTheater(findTheater(seatRowRequest));
    
    return new SeatRowResponse(seatRowRepository.save(seatRow));
  }

  public void delete(SeatRowRequest seatRowRequest) {
    SeatRow seatRow = seatRowRepository
      .findById(seatRowRequest.getId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    seatRowRepository.delete(seatRow);
  }

  private Theater findTheater(SeatRowRequest request) {
    return theaterRepository
      .findById(request.getTheaterId())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  private void setupSeats(List<Seat> seats, SeatRow seatRow) {
    for (Seat seat : seats) seat.setSeatRow(seatRow);
    seatRepository.saveAll(seats);
  }

  private List<Seat> findSeats(SeatRowRequest request) {
    return seatRepository.findAllById(request.getSeatIds());
  }

  private void clearSeats(SeatRow seatRow) {
    List<Seat> seats = seatRow.getSeats();
    for (Seat seat : seats) {
      seat.setSeatRow(null);
    }
  }

  private void updateSeats(SeatRow seatRow, List<Seat> seats) {
    for (Seat seat : seats) {
      seat.setSeatRow(seatRow);
    }
  }
}
