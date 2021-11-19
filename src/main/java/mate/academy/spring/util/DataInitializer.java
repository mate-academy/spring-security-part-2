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
    private final AuthenticationService authenticationService;
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(AuthenticationService authenticationService,
                           RoleService roleService,
                           UserService userService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = roleService.add(new Role(Role.RoleName.ADMIN));
        Role userRole = roleService.add(new Role(Role.RoleName.USER));
        authenticationService.register("bob@gmai.com", "123");
        User admin = new User();
        admin.setEmail("alica@gmail.com");
        admin.setPassword("456");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
    }
}
