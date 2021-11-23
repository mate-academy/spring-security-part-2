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
        Role adminRole = roleService.getRoleByName("ADMIN");
        adminRole.setRole(adminRole.getRole());
        roleService.add(adminRole);
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);

        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("password");
        Role userRole = roleService.getRoleByName("USER");
        userRole.setRole(userRole.getRole());
        roleService.add(userRole);
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
