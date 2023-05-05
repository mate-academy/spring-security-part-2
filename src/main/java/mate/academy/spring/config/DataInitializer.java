package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleNames;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(
            RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role();
        adminRole.setName(RoleNames.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleNames.USER);
        roleService.add(userRole);
        User user = new User();
        user.setEmail("admin");
        user.setPassword("1290");
        user.setRoles(Set.of(adminRole));
        userService.add(user);
    }
}
