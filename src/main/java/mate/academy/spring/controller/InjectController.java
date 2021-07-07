package mate.academy.spring.controller;

import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public InjectController(RoleService roleService, AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String injectData() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User("admin@mail.com", "12345678", Set.of(adminRole, userRole));
        User user = new User("user@mail.com", "12345678", Set.of(userRole));
        authenticationService.register(admin.getEmail(), admin.getPassword());
        authenticationService.register(user.getEmail(), user.getPassword());
        return "Done!";
    }
}
