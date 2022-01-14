package mate.academy.spring.config;

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
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role roleUser = new Role();
        roleUser.setName(RoleName.USER);
        Role roleAdmin = new Role();
        roleAdmin.setName(RoleName.ADMIN);
        roleService.add(roleUser);
        roleService.add(roleAdmin);
        User user = new User();
        user.setEmail("admin@mail.com");
        user.setPassword("admin");
        user.setRoles(Set.of(roleAdmin));
        userService.add(user);
    }
}
