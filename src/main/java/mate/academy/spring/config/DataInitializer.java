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

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User userAdmin = new User();
        userAdmin.setEmail("admin@i.ua");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(adminRole));
        userService.add(userAdmin);
        User userUser = new User();
        userUser.setEmail("user@i.ia");
        userUser.setPassword("user123");
        userUser.setRoles(Set.of(userRole));
        userService.add(userUser);
    }
}
