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
        Role adminRole = new Role();
        adminRole.setRole(Role.Authority.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRole(Role.Authority.USER);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(adminRole));
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("user123");
        user.setRoles(Set.of(userRole));
        userService.add(admin);
        userService.add(user);
    }
}
