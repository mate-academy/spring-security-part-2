package mate.academy.spring;

import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.CinemaHall;
import mate.academy.spring.model.Movie;
import mate.academy.spring.model.MovieSession;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.CinemaHallService;
import mate.academy.spring.service.MovieService;
import mate.academy.spring.service.MovieSessionService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {
    private RoleService roleService;
    private UserService userService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;
    private MovieSessionService movieSessionService;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService,
                           MovieService movieService, CinemaHallService cinemaHallService,
                           MovieSessionService movieSessionService) {
        this. roleService = roleService;
        this.userService = userService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.save(adminRole);
        Role userRole = new Role();
        userRole.setName("USER");
        roleService.save(userRole);

        User admin = new User();
        admin.setEmail("special@gmail.com");
        admin.setPassword("123456789");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        User user = new User();
        user.setEmail("veryImportantUser@gmail.com");
        user.setPassword("987654321");
        user.setRoles(Set.of(userRole));
        userService.add(user);

        Movie terminator = new Movie();
        terminator.setTitle("Terminator 2");
        terminator.setDescription("shooter");
        movieService.add(terminator);

        CinemaHall planetCinema = new CinemaHall();
        planetCinema.setDescription("4DIMax");
        planetCinema.setCapacity(165);
        cinemaHallService.add(planetCinema);

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(terminator);
        movieSession.setShowTime(LocalDateTime.of(2020, 2, 29, 16, 50));
        movieSession.setCinemaHall(planetCinema);
        movieSessionService.add(movieSession);
    }
}
