package mate.academy.spring.controller;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public String injectData() {
        if (roleService.getRoleByName("USER").isPresent()) {
            return "Nothing to inject";
        }
        System.out.println("inject start");
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleUser.setName("USER");

        roleService.add(roleAdmin);
        roleService.add(roleUser);

        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin123");
        admin.setRoles(Set.of(roleAdmin));
        userService.add(admin);

        User firstUser = new User();
        firstUser.setEmail("bob@gmail.com");
        firstUser.setPassword("12345");
        firstUser.setRoles(Set.of(roleUser));
        userService.add(firstUser);

        User secondUser = new User();
        secondUser.setEmail("alice@gmail.com");
        secondUser.setPassword("55555");
        secondUser.setRoles(Set.of(roleUser));
        userService.add(secondUser);

        return "Done";
    }
}
