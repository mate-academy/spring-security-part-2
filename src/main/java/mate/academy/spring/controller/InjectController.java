package mate.academy.spring.controller;

import mate.academy.spring.model.Role;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private RoleService roleService;
    private AuthenticationService authenticationService;
    private UserService userService;

    @Autowired
    public InjectController(RoleService roleService, AuthenticationService authenticationService,
                            UserService userService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @GetMapping
    public String injectData() {
        roleService.add(new Role(Role.RoleName.USER));
        roleService.add(new Role(Role.RoleName.ADMIN));
        authenticationService.register("alice@i.ua","1234");
        authenticationService.register("bob@i.ua","1234");
        return "Done!";
    }
}
