package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RolesEnum;
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
        adminRole.setName(RolesEnum.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RolesEnum.USER);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@mate.ua");
        admin.setPassword("12345");
        admin.setUserRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@mate.ua");
        user.setPassword("12345");
        user.setUserRoles(Set.of(userRole));
        userService.add(user);
    }
}
