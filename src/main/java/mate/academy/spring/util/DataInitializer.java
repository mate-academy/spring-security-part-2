package mate.academy.spring.util;

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
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));

        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("1234");
        bob.setRoles(Set.of(roleService.getRoleByName(Role.RoleName.USER)));
        userService.add(bob);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("12345");
        alice.setRoles(Set.of(roleService.getRoleByName(Role.RoleName.ADMIN)));
        userService.add(alice);
    }
}
