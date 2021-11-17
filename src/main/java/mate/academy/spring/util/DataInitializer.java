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
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);
        User firstUser = new User();
        firstUser.setEmail("boss@in.ua");
        firstUser.setPassword("qwerty");
        firstUser.setRoles(Set.of(adminRole));
        userService.add(firstUser);
        User secondUser = new User();
        secondUser.setEmail("hugo@in.ua");
        secondUser.setPassword("12345");
        secondUser.setRoles(Set.of(userRole));
        userService.add(secondUser);
    }
}
