package mate.academy.spring.controller;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class InjectController {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public InjectController(UserService userService,
                            RoleService roleService,
                            AuthenticationService authenticationService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/inject")
    private String  inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.add(userRole);
        Set<Role> userSet = new HashSet<>();
        userSet.add(userRole);
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(adminRole);

        authenticationService.register("bob@gmail.com", "1234", adminSet);
        authenticationService.register("alice@gmail.com", "1234", userSet);

        return "Done";


    }
}
