package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.model.UserRole;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private UserService userService;
    private RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(UserRole.ADMIN.name());
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(UserRole.USER.name());
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin.kozak@gmail.com");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("zara.benito@gmail.com");
        user.setPassword("qwerty123");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
