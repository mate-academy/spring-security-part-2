package mate.academy.spring.config;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
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
    public String inject() {
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("123456");
        Role adminsRole = new Role();
        adminsRole.setRole(Role.RoleType.ADMIN);
        Set<Role> adminRole = new HashSet<>();
        roleService.add(adminsRole);
        adminRole.add(adminsRole);
        admin.setRoles(adminRole);
        userService.add(admin);

        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("123456");
        Role usersRole = new Role();
        usersRole.setRole(Role.RoleType.USER);
        roleService.add(usersRole);
        Set<Role> userRole = new HashSet<>();
        userRole.add(usersRole);
        user.setRoles(userRole);
        userService.add(user);

        return "Injected!";
    }
}
