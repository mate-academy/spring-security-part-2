package mate.academy.spring.config;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Repository;

@Repository
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
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        Set<Role> userSet = new HashSet<>();
        userSet.add(userRole);
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(adminRole);

        authenticationService.register("bob@gmail.com", "1234", adminSet);
        authenticationService.register("alice@gmail.com", "1234", userSet);
    }
}
