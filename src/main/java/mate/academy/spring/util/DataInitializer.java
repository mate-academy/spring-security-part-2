package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        roleService.add(userRole);
        User adminUser = new User();
        adminUser.setEmail("bob@gmail.com");
        adminUser.setPassword("12345");
        adminUser.setRoles(Set.of(adminRole, userRole));
        userService.add(adminUser);
        User user = new User();
        user.setEmail("ani@gmail.com");
        user.setPassword("qwerty");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
