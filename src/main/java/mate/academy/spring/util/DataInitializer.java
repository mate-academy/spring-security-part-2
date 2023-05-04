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

    public DataInitializer(RoleService roleService,
                           UserService userService) {
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
        User user1 = new User();
        user1.setEmail("admin@i.ua");
        user1.setPassword("admin123");
        user1.setRoles(Set.of(adminRole));
        userService.add(user1);
        User user2 = new User();
        user2.setEmail("user@i.ua");
        user2.setPassword("user123");
        user2.setRoles(Set.of(userRole));
        userService.add(user2);
    }
}
