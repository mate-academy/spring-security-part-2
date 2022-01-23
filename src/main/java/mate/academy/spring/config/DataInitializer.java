package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.Roles;
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
        adminRole.setName(Roles.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Roles.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("bob@gmail.com");
        user.setPassword("12345");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}
