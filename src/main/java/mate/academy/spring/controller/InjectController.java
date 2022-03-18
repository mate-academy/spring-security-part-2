package mate.academy.spring.controller;

import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/inject")
public class InjectController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String injectData() {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
        authenticationService.register("bob@i.ua","1234");
        User alice = new User();
        alice.setEmail("alice@i.ua");
        alice.setPassword("1234");
        alice.setRoles(Set.of(roleService.getByName("ADMIN")));
        userService.add(alice);
        return "Done!";
    }
}
