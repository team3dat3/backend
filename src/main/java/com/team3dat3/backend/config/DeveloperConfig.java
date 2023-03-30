package com.team3dat3.backend.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.team3dat3.backend.dto.user.UserRequest;
import com.team3dat3.backend.entity.Achievement;
import com.team3dat3.backend.entity.Coupon;
import com.team3dat3.backend.entity.Movie;
import com.team3dat3.backend.entity.Reservation;
import com.team3dat3.backend.entity.Seat;
import com.team3dat3.backend.entity.SeatRow;
import com.team3dat3.backend.entity.Show;
import com.team3dat3.backend.entity.ShowDateTime;
import com.team3dat3.backend.entity.Theater;
import com.team3dat3.backend.entity.User;
import com.team3dat3.backend.repository.AchievementRepository;
import com.team3dat3.backend.repository.CouponRepository;
import com.team3dat3.backend.repository.MovieRepository;
import com.team3dat3.backend.repository.ReservationRepository;
import com.team3dat3.backend.repository.SeatRepository;
import com.team3dat3.backend.repository.SeatRowRepository;
import com.team3dat3.backend.repository.ShowDateTimeRepository;
import com.team3dat3.backend.repository.ShowRepository;
import com.team3dat3.backend.repository.TheaterRepository;
import com.team3dat3.backend.repository.UserRepository;
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
    private CouponRepository couponRepository;
    private AchievementRepository achievementRepository;

    public DeveloperConfig(
        UserService userService,
        UserRepository userRepository,
        ShowRepository showRepository,
        TheaterRepository theaterRepository,
        ReservationRepository reservationRepository,
        SeatRowRepository seatRowRepository,
        SeatRepository seatRepository,
        MovieRepository movieRepository,
        ShowDateTimeRepository showDateTimeRepository,
        CouponRepository couponRepository,
        AchievementRepository achievementRepository
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
        this.couponRepository = couponRepository;
        this.achievementRepository = achievementRepository;
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
        List<ShowDateTime> showDateTimes1 = Arrays.asList(
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(1)).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(2)).build())
        );

        // Create show date times
        List<ShowDateTime> showDateTimes2 = Arrays.asList(
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(1)).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(2)).build())
        );

        // Create show date times
        List<ShowDateTime> showDateTimes3 = Arrays.asList(
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(1)).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(2)).build())
        );

        // Create show date times
        List<ShowDateTime> showDateTimes4 = Arrays.asList(
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now()).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(1)).build()),
            showDateTimeRepository.save(ShowDateTime.builder().showDate(LocalDateTime.now().plusDays(2)).build())
        );

        // Create movies
        Movie movie1 = movieRepository.save(Movie.builder()
            .title("The Silence of the Lambs")
            .director("Jonathan Demme")
            .actors("Jodie Foster, Anthony Hopkins, Scott Glenn")
            .prodYear(1991)
            .rated("R")
            .description("A young FBI cadet must confide in an incarcerated and manipulative killer to receive his help on catching another serial killer who skins his victims.")
            .genre(Arrays.asList(new String[]{"Crime", "Drama", "Thriller"}))
            .runtime("118 min")
            .build());

        Movie movie2 = movieRepository.save(Movie.builder()
            .title("Psycho")
            .director("Alfred Hitchcock")
            .actors("Anthony Perkins, Janet Leigh, Vera Miles")
            .prodYear(1960)
            .rated("R")
            .description("A Phoenix secretary embezzles $40,000 from her employer's client, goes on the run, and checks into a remote motel run by a young man under the domination of his mother.")
            .genre(Arrays.asList(new String[]{"Horror", "Mystery", "Thriller"}))
            .runtime("109 min")
            .build());

        Movie movie3 = movieRepository.save(Movie.builder()
            .title("The Shining")
            .director("Stanley Kubrick")
            .actors("Jack Nicholson, Shelley Duvall, Danny Lloyd")
            .prodYear(1980)
            .rated("R")
            .description("A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.")
            .genre(Arrays.asList(new String[]{"Drama", "Horror"}))
            .runtime("146 min")
            .build());

        Movie movie4 = movieRepository.save(Movie.builder()
            .title("Get Out")
            .director("Jordan Peele")
            .actors("Daniel Kaluuya, Allison Williams, Bradley Whitford")
            .prodYear(2017)
            .rated("R")
            .description("A young African-American visits his white girlfriend's parents for the weekend, where his simmering uneasiness about their reception of him eventually reaches a boiling point.")
            .genre(Arrays.asList(new String[]{"Horror", "Mystery", "Thriller"}))
            .runtime("104 min")
            .build());

        // Create theaters
        Theater theater1 = theaterRepository.save(new Theater(0L, "Copenhagen"));
        Theater theater2 = theaterRepository.save(new Theater(0L, "Aarhus"));
        Theater theater3 = theaterRepository.save(new Theater(0L, "Aalborg"));
        Theater theater4 = theaterRepository.save(new Theater(0L, "Odense"));

        // Create shows
        Show show1 = showRepository.save(Show.builder().price(120).movie(movie1).theater(theater1).showDates(showDateTimes1).build());
        Show show2 = showRepository.save(Show.builder().price(100).movie(movie2).theater(theater2).showDates(showDateTimes2).build());
        Show show3 = showRepository.save(Show.builder().price(80).movie(movie3).theater(theater3).showDates(showDateTimes3).build());
        Show show4 = showRepository.save(Show.builder().price(60).movie(movie4).theater(theater4).showDates(showDateTimes4).build());


        // Add shows to show date times
        showDateTimes1.forEach(showDateTime -> showDateTime.setShow(show1));
        showDateTimes2.forEach(showDateTime -> showDateTime.setShow(show2));
        showDateTimes3.forEach(showDateTime -> showDateTime.setShow(show3));
        showDateTimes4.forEach(showDateTime -> showDateTime.setShow(show4));

        // Save show date times
        showDateTimeRepository.saveAll(showDateTimes1);
        showDateTimeRepository.saveAll(showDateTimes2);
        showDateTimeRepository.saveAll(showDateTimes3);
        showDateTimeRepository.saveAll(showDateTimes4);

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

        // Create reservations
        reservationRepository.save(new Reservation(user1, show1, seats1, showDateTimes1.get(0)));
        reservationRepository.save(new Reservation(user1, show3, seats1, showDateTimes2.get(0)));
        reservationRepository.save(new Reservation(user1, show4, seats1, showDateTimes3.get(0)));
        reservationRepository.save(new Reservation(user2, show2, seats1, showDateTimes4.get(0)));
        reservationRepository.save(new Reservation(user2, show3, seats1, showDateTimes1.get(0)));
        reservationRepository.save(new Reservation(user2, show4, seats1, showDateTimes2.get(0)));

        // Create seat rows
        SeatRow seatRow1 = seatRowRepository.save(new SeatRow(seats1, theater1));
        SeatRow seatRow2 = seatRowRepository.save(new SeatRow(seats2, theater2));
        SeatRow seatRow3 = seatRowRepository.save(new SeatRow(seats3, theater3));
        SeatRow seatRow4 = seatRowRepository.save(new SeatRow(seats4, theater4));

        for (Seat seat : seats1)
            seat.setSeatRow(seatRow1);
        seatRepository.saveAll(seats1);

        for (Seat seat : seats2)
            seat.setSeatRow(seatRow2);
        seatRepository.saveAll(seats2);

        for (Seat seat : seats3)
            seat.setSeatRow(seatRow3);
        seatRepository.saveAll(seats3);

        for (Seat seat : seats4)
            seat.setSeatRow(seatRow4);
        seatRepository.saveAll(seats4);

        // Create coupons
        couponRepository.save(new Coupon("123", 10, 10));
        couponRepository.save(new Coupon("456", 20, 20));
        couponRepository.save(new Coupon("789", 30, 30));

        // Create achievements
        achievementRepository.save(new Achievement());
        achievementRepository.save(new Achievement());
        achievementRepository.save(new Achievement());
    }
}
