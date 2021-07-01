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
        roleService.add(new Role(Role.RoleName.ROLE_ADMIN));
        roleService.add(new Role(Role.RoleName.ROLE_USER));

        userService.add(new User("max@gmail.com", "max",
                Set.of(roleService.getRoleByName(Role.RoleName.ROLE_ADMIN))));
        userService.add(new User("bob@gmail.com", "bob",
                Set.of(roleService.getRoleByName(Role.RoleName.ROLE_USER))));
        userService.add(new User("name@gmail.com", "name",
                Set.of(roleService.getRoleByName(Role.RoleName.ROLE_USER),
                        roleService.getRoleByName(Role.RoleName.ROLE_ADMIN))));
    }
}
