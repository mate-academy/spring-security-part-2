package mate.academy.spring.model;

import java.util.Set;
import javax.annotation.PostConstruct;
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

        Role moderatorRole = new Role();
        moderatorRole.setRoleName(Role.RoleName.MODERATOR);
        roleService.add(moderatorRole);

        User userAdmin = new User();
        userAdmin.setEmail("admin");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(adminRole));
        userService.add(userAdmin);

        User user = new User();
        user.setEmail("user");
        user.setPassword("user123");
        user.setRoles(Set.of(userRole));
        userService.add(user);

        User userAdminAndUser = new User();
        userAdminAndUser.setEmail("superuser");
        userAdminAndUser.setPassword("superuser123");
        userAdminAndUser.setRoles(Set.of(adminRole, userRole));
        userService.add(userAdminAndUser);
    }
}
