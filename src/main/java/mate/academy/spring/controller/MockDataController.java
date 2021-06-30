package mate.academy.spring.controller;

import java.time.LocalDateTime;
import mate.academy.spring.model.CinemaHall;
import mate.academy.spring.model.Movie;
import mate.academy.spring.model.MovieSession;
import mate.academy.spring.service.CinemaHallService;
import mate.academy.spring.service.MovieService;
import mate.academy.spring.service.MovieSessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class MockDataController {
    private final MovieSessionService movieSessionService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    
    public MockDataController(MovieSessionService movieSessionService,
            CinemaHallService cinemaHallService, MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }
    
    @GetMapping
    public String injectMockData() {
        CinemaHall redHall = new CinemaHall();
        redHall.setCapacity(100);
        redHall.setDescription("RED hall");
    
        CinemaHall blackHall = new CinemaHall();
        blackHall.setCapacity(150);
        blackHall.setDescription("BLACK hall");
    
        cinemaHallService.add(redHall);
        cinemaHallService.add(blackHall);
    
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
    
        Movie matrix = new Movie();
        matrix.setTitle("Matrix");
        matrix.setDescription(
                "The Matrix is a computer-generated dream world designed to keep these humans "
                        + "under control.");
    
        movieService.add(fastAndFurious);
        movieService.add(matrix);
    
        MovieSession fastAndFuriousRedHall = new MovieSession();
        fastAndFuriousRedHall.setCinemaHall(redHall);
        fastAndFuriousRedHall.setMovie(fastAndFurious);
        fastAndFuriousRedHall.setShowTime(LocalDateTime.now());
    
        MovieSession matrixBlackHall = new MovieSession();
        matrixBlackHall.setMovie(matrix);
        matrixBlackHall.setCinemaHall(blackHall);
        matrixBlackHall.setShowTime(LocalDateTime.now().plusDays(3L));
        movieSessionService.add(fastAndFuriousRedHall);
        movieSessionService.add(matrixBlackHall);
        return "Insert to DB!";
    }
}
