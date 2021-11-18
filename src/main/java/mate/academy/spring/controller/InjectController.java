package mate.academy.spring.controller;

import java.util.HashSet;
import java.util.Set;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String injectData() {
        roleService.add(new Role(Role.RoleName.ADMIN));
        roleService.add(new Role(Role.RoleName.USER));
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Role.RoleName.ADMIN));
        user.setRoles(roles);
        user.setEmail("bob@ukr.net");
        user.setPassword("1234");
        authenticationService.register(user.getEmail(), user.getPassword());
        return "done";
    }
}
