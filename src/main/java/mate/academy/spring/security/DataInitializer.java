package mate.academy.spring.security;

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
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(("USER"));
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("root@i.ua");
        admin.setPassword("1111");
        admin.setRoles(Set.of(adminRole));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("2222");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
