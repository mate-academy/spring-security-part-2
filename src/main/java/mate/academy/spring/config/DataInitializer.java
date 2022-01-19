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
        Role admin = new Role();
        admin.setRoleType(Role.RoleType.ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleType(Role.RoleType.USER);
        roleService.add(user);
        User userUser = new User();
        userUser.setEmail("userUser@gmail.com");
        userUser.setPassword("123123");
        userUser.setRoles(Set.of(user));
        User userAdmin = new User();
        userAdmin.setEmail("userAdmin@gmail.com");
        userAdmin.setPassword("123123");
        userAdmin.setRoles(Set.of(admin));
        userService.add(userAdmin);
        userService.add(userUser);
    }
}
