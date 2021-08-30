package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.Role.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService,
            UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role admin = new Role();
        admin.setName(RoleName.ADMIN);
        roleService.add(admin);

        Role user = new Role();
        user.setName(RoleName.USER);
        roleService.add(user);

        User adminUser = new User();
        adminUser.setEmail("admin@admin.ua");
        adminUser.setPassword("admin");
        adminUser.setRoles(Set.of(admin));
        userService.add(adminUser);

        User simpleUser = new User();
        simpleUser.setEmail("simple_user@user.ua");
        simpleUser.setPassword("simple_user");
        simpleUser.setRoles(Set.of(user));
        userService.add(simpleUser);
    }
}
