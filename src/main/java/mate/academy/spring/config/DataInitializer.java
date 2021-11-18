package mate.academy.spring.config;

import java.util.Set;
import javax.annotation.PostConstruct;
import mate.academy.spring.model.Role;
import mate.academy.spring.model.RoleName;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private AuthenticationService authenticationService;
    private RoleService roleService;

    public DataInitializer(AuthenticationService authenticationService, RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName(RoleName.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setName(RoleName.USER);
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("12345678");
        admin.setRoles(Set.of(adminRole));
        authenticationService.register(admin.getEmail(), admin.getPassword());
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("87654321");
        user.setRoles(Set.of(userRole));
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
