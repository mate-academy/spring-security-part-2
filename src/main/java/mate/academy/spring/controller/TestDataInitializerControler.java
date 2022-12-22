package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.security.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class TestDataInitializerControler {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;
    private final UserService userService;

    public TestDataInitializerControler(AuthenticationService authenticationService,
                                        RoleService roleService,
                                        UserService userService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping
    public String inject() {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
        authenticationService.register("jane@mail.com","password");
        User john = new User();
        john.setEmail("john@mail.com");
        john.setPassword("password");
        john.setAuthorities(Set.of(roleService.getByName("ADMIN")));
        userService.add(john);
        return "Data was inected.";
    }
}
