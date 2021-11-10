package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public DataInitializer(UserService userService,
                           RoleService roleService,
                           AuthenticationService authenticationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void injectData() {
        roleService.add(new Role(RoleName.ADMIN));
        roleService.add(new Role(RoleName.USER));

        User bob = new User();
        bob.setEmail("bob@test.com");
        bob.setPassword("1234");
        bob.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(bob);

        User aliceUser = new User();
        aliceUser.setEmail("alice@test.com");
        aliceUser.setPassword("12345");
        authenticationService.register(aliceUser.getEmail(), aliceUser.getPassword());
    }
}
