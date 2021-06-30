package mate.academy.spring;

import java.time.LocalDateTime;
import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.CinemaHall;
import mate.academy.spring.model.Movie;
import mate.academy.spring.model.MovieSession;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.CinemaHallService;
import mate.academy.spring.service.MovieService;
import mate.academy.spring.service.MovieSessionService;
import mate.academy.spring.service.OrderService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final AuthenticationService service;
    private final ShoppingCartService cartService;
    private final OrderService orderService;

    public UserInitializer(RoleService roleService, UserService userService,
                           MovieService movieService, CinemaHallService cinemaHallService,
                           MovieSessionService movieSessionService, AuthenticationService service,
                           ShoppingCartService cartService, OrderService orderService) {
        this.roleService = roleService;
        this.userService = userService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.service = service;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void injectUser() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);

        User user = new User();
        user.setEmail("root@root.com");
        user.setPassword("1234");
        user.setRoles(Set.of(userRole));
        userService.add(user);

        User admin = new User();
        admin.setEmail("admin@root.com");
        admin.setPassword("1234");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        User chris = new User();
        chris.setEmail("Chris_Pain@gmail.com");
        chris.setPassword("1478963");
        chris.setRoles(Set.of(userRole));

        User veronica = new User();
        veronica.setEmail("Veronica_Porter@gmail.com");
        veronica.setPassword("ohjrlio");
        veronica.setRoles(Set.of(userRole));
    }
}
