package mate.academy.spring.service;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.model.enums.RoleEnum;
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
        adminRole.setName(RoleEnum.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleEnum.USER);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@mate.ua");
        admin.setPassword("12345");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@mate.ua");
        user.setPassword("12345");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
