package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("1234");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("1234");
        user.setRoles(Set.of(userRole));
        userService.add(user);
        User multi = new User();
        multi.setEmail("multi@i.ua");
        multi.setPassword("1234");
        multi.setRoles(Set.of(userRole, adminRole));
        userService.add(multi);
    }
}
