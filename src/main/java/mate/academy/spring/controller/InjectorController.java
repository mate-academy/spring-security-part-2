package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InjectorController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public InjectorController(UserService userService,
                              AuthenticationService authenticationService,
                              RoleService roleService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @GetMapping("/inject")
    public String injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ROLE_ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(RoleName.ROLE_USER);
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("alice@gmail.com");
        admin.setPassword("12345");
        admin.setRoles(Set.of(roleService.getByName(String.valueOf(RoleName.ROLE_ADMIN))));
        userService.add(admin);

        User user = new User();
        user.setEmail("bob@gmail.com");
        user.setPassword("54321");
        user.setRoles(Set.of(roleService.getByName(String.valueOf(RoleName.ROLE_USER))));
        userService.add(user);

        return "Data was injected.";
    }
}
