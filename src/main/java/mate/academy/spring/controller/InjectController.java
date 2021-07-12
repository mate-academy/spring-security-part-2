package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public InjectController(UserService userService, RoleService roleService,
                            AuthenticationService authenticationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        roleService.add(adminRole);
        authenticationService.register("dungeonmaster@gmail.com","1234");
        User user = new User();
        user.setEmail("master@gmail.com");
        user.setPassword("1234");
        user.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(user);
        return "Data injected successfully";
    }
}
