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
    public String injectRoles() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
        return "Roles were added successfully!";
    }

    @PostConstruct
    public String injectUsers() {
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("12345678");
        Role adminRole = roleService.getRoleByName(Role.RoleName.ADMIN);
        Role userRole = roleService.getRoleByName(Role.RoleName.USER);
        bob.setRoles(Set.of(adminRole, userRole));
        userService.add(bob);
        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("12345678");
        alice.setRoles(Set.of(userRole));
        userService.add(alice);
        return "Users were added successfully!";
    }
}
