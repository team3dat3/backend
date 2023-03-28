package com.team3dat3.backend.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.MovieRepository;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.SeatRowRepository;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.TheaterRepository;
import com.team3dat3.backend.repository.UserRepository;
import com.team3dat3.backend.service.ShowService;
import com.team3dat3.backend.service.UserService;


/*
 * Author: Nicolai Berg Andersen
 * Date: 2023-03-26
 * Description: Developer config
 */

@Configuration
public class DeveloperConfig implements ApplicationRunner {

    private UserService userService;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private TheaterRepository theaterRepository;
    private ReservationRepository reservationRepository;
    private SeatRowRepository seatRowRepository;
    private SeatRepository seatRepository;
    private MovieRepository movieRepository;
    private ShowDateTimeRepository showDateTimeRepository;

    public DeveloperConfig(
        UserService userService,
        UserRepository userRepository,
        ShowRepository showRepository,
        TheaterRepository theaterRepository,
        ReservationRepository reservationRepository,
        SeatRowRepository seatRowRepository,
        SeatRepository seatRepository,
        MovieRepository movieRepository,
        ShowDateTimeRepository showDateTimeRepository
    ) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.theaterRepository = theaterRepository;
        this.reservationRepository = reservationRepository;
        this.seatRowRepository = seatRowRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
        this.showDateTimeRepository = showDateTimeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create users with service to ensure password is hashed
        userService.create(new UserRequest(new User("admin", "admin", "mail1@eg.com", "12345678", new String[] {"ADMIN"})));
        userService.create(new UserRequest(new User("member", "member", "mail2@eg.com", "87654321", new String[] {"MEMBER"})));
        userService.create(new UserRequest(new User("member1", "member", "mail3@eg.com", "87654321", new String[] {"MEMBER"})));
        userService.create(new UserRequest(new User("member2", "member", "mail4@eg.com", "87654321", new String[] {"MEMBER"})));
        userService.create(new UserRequest(new User("member3", "member", "mail5@eg.com", "87654321", new String[] {"MEMBER"})));
        // Fetch user 1
        User user1 = userRepository.findById("admin").get();
        User user2 = userRepository.findById("member").get();

        // Set user1 as admin
        user1.setRoles(Arrays.asList("ADMIN"));
        userRepository.save(user1);

        // Create show date times
        showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build());
        showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build());
        showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build());

        // Create shows
        Show show1 = showRepository.save(Show.builder().price(120).build());
        Show show2 = showRepository.save(Show.builder().price(100).build());
        Show show3 = showRepository.save(Show.builder().price(80).build());
        Show show4 = showRepository.save(Show.builder().price(60).build());

        // Create theaters
        Theater theater1 = theaterRepository.save(new Theater());
        Theater theater2 = theaterRepository.save(new Theater());
        Theater theater3 = theaterRepository.save(new Theater());
        Theater theater4 = theaterRepository.save(new Theater());

        // Create reservations
        reservationRepository.save(new Reservation(user1, show1));
        reservationRepository.save(new Reservation(user1, show3));
        reservationRepository.save(new Reservation(user1, show4));
        reservationRepository.save(new Reservation(user2, show2));
        reservationRepository.save(new Reservation(user2, show3));
        reservationRepository.save(new Reservation(user2, show4));
        
        // Create seats
        List<Seat> seats1 = seatRepository.saveAll(Arrays.asList(
            new Seat[] {
                new Seat(), new Seat(), new Seat(), new Seat(), new Seat()
            }
        ));

        List<Seat> seats2 = seatRepository.saveAll(Arrays.asList(
            new Seat[] {
                new Seat(), new Seat(), new Seat(), new Seat(), new Seat()
            }
        ));

        List<Seat> seats3 = seatRepository.saveAll(Arrays.asList(
            new Seat[] {
                new Seat(), new Seat(), new Seat(), new Seat(), new Seat()
            }
        ));

        List<Seat> seats4 = seatRepository.saveAll(Arrays.asList(
            new Seat[] {
                new Seat(), new Seat(), new Seat(), new Seat(), new Seat()
            }
        ));

        // Create seat rows
        seatRowRepository.save(new SeatRow(seats1, theater1));
        seatRowRepository.save(new SeatRow(seats2, theater2));
        seatRowRepository.save(new SeatRow(seats3, theater3));
        seatRowRepository.save(new SeatRow(seats4, theater4));

        // Create movies
        movieRepository.save(Movie.builder()
            .title("1")
            .director("1")
            .actors("1")
            .prodYear(1901)
            .ageLimit(12)
            .description("blabla")
            .genre(Arrays.asList(new String[]{"Horror", "Thriller"}))
            .runtime("142 min")
            .build());

        movieRepository.save(Movie.builder()
            .title("2")
            .director("2")
            .actors("2")
            .prodYear(1901)
            .ageLimit(12)
            .description("blabla")
            .genre(Arrays.asList(new String[]{"Horror", "Thriller"}))
            .runtime("142 min")
            .build());

        movieRepository.save(Movie.builder()
            .title("3")
            .director("3")
            .actors("3")
            .prodYear(1901)
            .ageLimit(12)
            .description("blabla")
            .genre(Arrays.asList(new String[]{"Horror", "Thriller"}))
            .runtime("142 min")
            .build());

        movieRepository.save(Movie.builder()
            .title("4")
            .director("4")
            .actors("4")
            .prodYear(1901)
            .ageLimit(12)
            .description("blabla")
            .genre(Arrays.asList(new String[]{"Horror", "Thriller"}))
            .runtime("142 min")
            .build());
    }
}
