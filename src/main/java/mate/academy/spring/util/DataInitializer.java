package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
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
        adminRole.setName(RoleName.ROLE_ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setName(RoleName.ROLE_USER);
        roleService.add(userRole);

        User bob = new User();
        bob.setEmail("bob@i.ua");
        bob.setPassword("123");
        bob.setRoles(Set.of(roleService.getRoleByName(RoleName.ROLE_ADMIN)));
        userService.add(bob);

        User alice = new User();
        alice.setEmail("alice@i.ua");
        alice.setPassword("1234");
        alice.setRoles(Set.of(userRole));
        userService.add(alice);
    }
}
