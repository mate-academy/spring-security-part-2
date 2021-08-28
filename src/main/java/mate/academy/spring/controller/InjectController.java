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
    private final RoleService roleService;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public InjectController(RoleService roleService,
                            AuthenticationService authenticationService,
                            UserService userService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping
    public String injectData() {
        roleService.add(new Role(Role.RoleName.USER));
        roleService.add(new Role(Role.RoleName.ADMIN));
        authenticationService.register("user@test.com", "1q2w3e4r");
        User adminUser = new User();
        adminUser.setEmail("admin@test.com");
        adminUser.setPassword("1q2w3e4r");
        adminUser.setRoles(Set.of(roleService.getRoleByName(Role.RoleName.ADMIN)));
        userService.add(adminUser);
        return "Done";
    }
}
