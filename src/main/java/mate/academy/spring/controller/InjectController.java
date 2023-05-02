package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;

    public InjectController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public void injectData() {
        Role admin = new Role();
        admin.setRoleName(Role.RoleName.ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName(Role.RoleName.USER);
        roleService.add(user);

        User userAdmin = new User();
        userAdmin.setEmail("dominskyj@gmail.com");
        userAdmin.setPassword("1234");
        userAdmin.setRoles(Set.of(admin, user));
        userService.add(userAdmin);

        User bob = new User();
        bob.setEmail("bob@i.ua");
        bob.setPassword("1234");
        bob.setRoles(Set.of(user));
        userService.add(bob);
    }
}
