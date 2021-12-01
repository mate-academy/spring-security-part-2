package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);

        User admin = new User();
        admin.setRoles(Set.of(adminRole));
        admin.setEmail("bob@gmail.com");
        admin.setPassword("12345678");
        userService.add(admin);

        User user = new User();
        user.setRoles(Set.of(userRole));
        user.setEmail("petro@gmail.com");
        user.setPassword("87654321");
        userService.add(user);

        User userSecond = new User();
        userSecond.setRoles(Set.of(userRole));
        userSecond.setEmail("alice@gmail.com");
        userSecond.setPassword("00000000");
        userService.add(userSecond);
    }
}
