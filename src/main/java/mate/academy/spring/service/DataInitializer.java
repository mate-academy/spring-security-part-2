package mate.academy.spring.service;

import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.CinemaHall;
import mate.academy.spring.model.Movie;
import mate.academy.spring.model.MovieSession;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(MovieSessionService movieSessionService, MovieService movieService,
                           CinemaHallService cinemaHallService, RoleService roleService,
                           UserService userService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);

        Movie movieTerminator = new Movie();
        movieTerminator.setTitle("The Terminator");
        movieTerminator.setDescription("Action with Arnold Schwarzenegger. 1984");
        movieService.add(movieTerminator);

        CinemaHall yellow = new CinemaHall();
        yellow.setCapacity(20);
        yellow.setDescription("Yellow hall");
        cinemaHallService.add(yellow);

        CinemaHall green = new CinemaHall();
        green.setCapacity(80);
        green.setDescription("Green hall");
        cinemaHallService.add(green);

        MovieSession msFastAndFurious1 = new MovieSession(fastAndFurious, yellow,
                LocalDateTime.of(2021, 9, 12, 9, 0));
        movieSessionService.add(msFastAndFurious1);

        MovieSession msFastAndFurious2 = new MovieSession(fastAndFurious, yellow,
                LocalDateTime.of(2021, 9, 12, 12, 0));
        movieSessionService.add(msFastAndFurious2);

        MovieSession msFastAndFurious3 = new MovieSession(fastAndFurious, green,
                LocalDateTime.of(2021, 9, 12, 18, 0));
        movieSessionService.add(msFastAndFurious3);

        MovieSession msFastAndFurious4 = new MovieSession(fastAndFurious, yellow,
                LocalDateTime.of(2021, 9, 14, 9, 0));
        movieSessionService.add(msFastAndFurious4);

        MovieSession msFastAndFurious5 = new MovieSession(fastAndFurious, green,
                LocalDateTime.of(2021, 9, 14, 9, 0));
        movieSessionService.add(msFastAndFurious5);

        MovieSession msTerminator1 = new MovieSession(movieTerminator, green,
                LocalDateTime.of(2021, 9, 12, 9, 0));
        movieSessionService.add(msTerminator1);

        MovieSession msTerminator2 = new MovieSession(movieTerminator, yellow,
                LocalDateTime.of(2021, 9, 12, 18, 0));
        movieSessionService.add(msTerminator2);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.add(roleUser);
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.add(roleAdmin);

        User userAdmin = new User();
        userAdmin.setEmail("admin@i.ua");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(roleAdmin));
        userService.add(userAdmin);
    }
}
