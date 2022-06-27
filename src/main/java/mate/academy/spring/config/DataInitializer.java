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
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);

        User bobUser = new User();
        bobUser.setEmail("bob@i.ua");
        bobUser.setPassword("12345678");
        bobUser.setRoles(Set.of(adminRole));
        userService.add(bobUser);

        User aliceUser = new User();
        aliceUser.setEmail("alice@i.ua");
        aliceUser.setPassword("87654321");
        aliceUser.setRoles(Set.of(userRole));
        userService.add(aliceUser);
    }
}
