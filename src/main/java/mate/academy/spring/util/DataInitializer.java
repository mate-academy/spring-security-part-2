package mate.academy.spring.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public DataInitializer(RoleService roleService, AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.valueOf("ADMIN"));
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.valueOf("USER"));
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin@i.ua");
        admin.setPassword("admin@i.ua");
        admin.setRoles(Set.of(adminRole));
        authenticationService.register(admin.getEmail(), admin.getPassword());
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("user@i.ua");
        user.setRoles(Set.of(userRole));
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
