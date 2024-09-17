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
        User user = new User();
        user.setEmail("anna@admin.ua");
        user.setPassword("12345678");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
        User user2 = new User();
        user2.setEmail("anna@user.ua");
        user2.setPassword("12345678");
        user2.setRoles(Set.of(userRole));
        userService.add(user2);
    }
}
