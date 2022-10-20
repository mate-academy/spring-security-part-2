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
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRole(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRole(Role.RoleName.USER);
        roleService.add(userRole);
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("bob123");
        bob.setRoles(Set.of(adminRole));
        userService.add(bob);
        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("alice123");
        alice.setRoles(Set.of(adminRole));
        userService.add(alice);
    }
}
