package mate.academy.spring.dao.impl;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleType;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Repository;

@Repository
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
        adminRole.setName(RoleType.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName(RoleType.USER);
        roleService.add(userRole);
        User bob = new User();
        bob.setEmail("admin@i.ua");
        bob.setPassword("admin123");
        bob.setRoles(Set.of(adminRole));
        User alice = new User();
        alice.setEmail("2@i.ua");
        alice.setPassword("2222");
        alice.setRoles(Set.of(userRole));
        userService.add(bob);
        userService.add(alice);
    }
}
