package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role admin = new Role();
        admin.setName("ADMIN");
        roleService.add(admin);
        Role user = new Role();
        user.setName("USER");
        roleService.add(user);

        User superUser = new User();
        superUser.setEmail("bob@gmail.com");
        superUser.setPassword("superuser");
        superUser.setRoles(Set.of(admin, user));
        userService.add(superUser);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("admin");
        alice.setRoles(Set.of(admin));
        userService.add(alice);

        User john = new User();
        john.setEmail("john@gmail.com");
        john.setPassword("1234");
        john.setRoles(Set.of(user));
        userService.add(john);
    }
}
