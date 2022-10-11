package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {
    private final AuthenticationService authService;
    private final RoleService roleService;

    public DataInitializer(AuthenticationService authService, RoleService roleService) {
        this.authService = authService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.add(userRole);
        Role adminRole = new Role();
        adminRole.setName(RoleName.ADMIN);
        roleService.add(adminRole);

        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("12345678");
        bob.setRoles(Set.of(roleService.getByName(RoleName.USER.name()).get()));
        authService.register(bob);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("12345678");
        alice.setRoles(Set.of(roleService.getByName(RoleName.USER.name()).get()));
        authService.register(alice);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345678");
        admin.setRoles(Set.of(roleService.getByName(RoleName.USER.name()).get(),
                roleService.getByName(RoleName.ADMIN.name()).get()));
        authService.register(admin);
    }
}
