package mate.academy.spring;

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
        adminRole.setName(RoleName.ROLE_ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleName.ROLE_USER);
        roleService.add(userRole);
        User ben = new User();
        ben.setEmail("ben@i.ua");
        ben.setPassword("1234");
        ben.setRoles(Set.of(adminRole));
        userService.add(ben);
        User alex = new User();
        alex.setEmail("alex@i.ua");
        alex.setPassword("1234");
        alex.setRoles(Set.of(userRole));
        userService.add(alex);
    }
}
