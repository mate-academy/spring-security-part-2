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
        User userOne = new User();
        userOne.setEmail("admin@mail.ua");
        userOne.setPassword("admin123");
        userOne.setRoles(Set.of(adminRole));
        User userTwo = new User();
        userTwo.setEmail("user@mail.ua");
        userTwo.setPassword("user123");
        userTwo.setRoles(Set.of(userRole));
        userService.add(userOne);
        userService.add(userTwo);
    }
}
