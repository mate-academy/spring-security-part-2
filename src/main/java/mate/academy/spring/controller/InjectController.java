package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;

    public InjectController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN.name());
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER.name());
        roleService.add(userRole);

        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("1234");
        bob.setRoles(Set.of(adminRole));
        userService.add(bob);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("5632");
        alice.setRoles(Set.of(userRole));
        userService.add(alice);
        return "Done!";
    }
}
