package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public DataInitializer(RoleService roleService,
                           UserService userService,
                           AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void inject() {
        Role admin = new Role();
        Role user = new Role();
        User alex = new User();
        alex.setEmail("alex@mail.ru");
        alex.setPassword("12345");
        alex.setRoles(Set.of(roleService.get("ADMIN")));
        userService.add(alex);
        authenticationService.register("alina@mail.ua", "qwerty");
    }
}
