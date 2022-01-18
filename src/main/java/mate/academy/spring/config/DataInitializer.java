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
        Role firstAdmin = new Role();
        firstAdmin.setRole(Role.Authority.ADMIN);
        roleService.add(firstAdmin);
        Role firstUser = new Role();
        firstUser.setRole(Role.Authority.USER);
        roleService.add(firstUser);
        User admin = new User();
        admin.setEmail("firstAdmin@gmail.com");
        admin.setPassword("123456");
        admin.setRoles(Set.of(firstAdmin));
        User user = new User();
        user.setEmail("firstUser@gmail.com");
        user.setPassword("654321");
        user.setRoles(Set.of(firstUser));
        userService.add(admin);
        userService.add(user);
    }
}
