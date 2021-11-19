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
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("password");
        Role adminRole = new Role();
        adminRole.setRole(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("password");
        Role userRole = new Role();
        userRole.setRole(Role.RoleName.USER);
        roleService.add(userRole);
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
