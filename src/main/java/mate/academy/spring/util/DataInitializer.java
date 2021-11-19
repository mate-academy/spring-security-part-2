package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;

public class DataInitializer {
    private AuthenticationService authenticationService;
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
        User userBob = new User();
        userBob.setEmail("bob@gmai.com");
        userBob.setPassword("123");
        userBob.setRoles(Set.of(adminRole));
        userService.add(userBob);
        roleService.add(new Role(Role.RoleName.USER));
        authenticationService.register(userBob.getEmail(),
                userBob.getPassword(), userBob.getRoles());
    }
}
